// Copyright (c) 2017-present boyw165
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
//    The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
//    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

#include <jni.h>
#include <android/log.h>
#include <android/bitmap.h>
#include <dlib/image_processing/frontal_face_detector.h>
#include <dlib/image_processing.h>
#include <dlib/image_io.h>
#include <my/jni.h>
#include <my/profiler.h>
#include <my/dlib/data/messages.pb.h>

#define LOGI(...) \
  ((void)__android_log_print(ANDROID_LOG_INFO, "dlib-jni:", __VA_ARGS__))

#define JNI_METHOD(NAME) \
    Java_com_my_jni_dlib_FaceLandmarksDetector_##NAME

using namespace ::com::my::jni::dlib::data;

// FIXME: Create a class inheriting from dlib::array2d<dlib::rgb_pixel>.
void convertBitmapToArray2d(JNIEnv* env,
                            jobject bitmap,
                            dlib::array2d<dlib::rgb_pixel>& out) {
    AndroidBitmapInfo bitmapInfo;
    void* pixels;
    int state;

    if (0 > (state = AndroidBitmap_getInfo(env, bitmap, &bitmapInfo))) {
        LOGI("L%d: AndroidBitmap_getInfo() failed! error=%d", __LINE__, state);
        throwException(env, "AndroidBitmap_getInfo() failed!");
        return;
    } else if (bitmapInfo.format != ANDROID_BITMAP_FORMAT_RGBA_8888) {
        LOGI("L%d: Bitmap format is not RGB_565!", __LINE__);
        throwException(env, "Bitmap format is not RGB_565!");
    }

    // Lock the bitmap for copying the pixels safely.
    if (0 > (state = AndroidBitmap_lockPixels(env, bitmap, &pixels))) {
        LOGI("L%d: AndroidBitmap_lockPixels() failed! error=%d", __LINE__, state);
        throwException(env, "AndroidBitmap_lockPixels() failed!");
        return;
    }

    LOGI("L%d: info.width=%d, info.height=%d", __LINE__, bitmapInfo.width, bitmapInfo.height);
    out.set_size((long) bitmapInfo.height, (long) bitmapInfo.width);

    char* line = (char*) pixels;
    for (int h = 0; h < bitmapInfo.height; ++h) {
        for (int w = 0; w < bitmapInfo.width; ++w) {
            uint32_t* color = (uint32_t*) (line + 4 * w);

            out[h][w].red = (unsigned char) (0xFF & ((*color) >> 24));
            out[h][w].green = (unsigned char) (0xFF & ((*color) >> 16));
            out[h][w].blue = (unsigned char) (0xFF & ((*color) >> 8));
        }

        line = line + bitmapInfo.stride;
    }

    // Unlock the bitmap.
    AndroidBitmap_unlockPixels(env, bitmap);
}

// JNI ////////////////////////////////////////////////////////////////////////

dlib::shape_predictor sFaceLandmarksPredictor;
dlib::frontal_face_detector sFaceDetector;

extern "C" JNIEXPORT jboolean JNICALL
JNI_METHOD(isFaceDetectorReady)(JNIEnv* env,
                                jobject thiz) {
    if (sFaceDetector.num_detectors() > 0) {
        return JNI_TRUE;
    } else {
        return JNI_FALSE;
    }
}

extern "C" JNIEXPORT jboolean JNICALL
JNI_METHOD(isFaceLandmarksDetectorReady)(JNIEnv* env,
                                         jobject thiz) {
    if (sFaceLandmarksPredictor.num_parts() > 0) {
        return JNI_TRUE;
    } else {
        return JNI_FALSE;
    }
}

extern "C" JNIEXPORT void JNICALL
JNI_METHOD(prepareFaceDetector)(JNIEnv *env,
                                jobject thiz) {
    // Profiler.
    Profiler profiler;
    profiler.start();

    // Prepare the detector.
    sFaceDetector = dlib::get_frontal_face_detector();

    double interval = profiler.stopAndGetInterval();

    LOGI("L%d: sFaceDetector is initialized (took %.3f ms)", __LINE__, interval);
    LOGI("L%d: sFaceDetector.num_detectors()=%lu", __LINE__, sFaceDetector.num_detectors());
}

extern "C" JNIEXPORT void JNICALL
JNI_METHOD(prepareFaceLandmarksDetector)(JNIEnv *env,
                                         jobject thiz,
                                         jstring detectorPath) {
    const char *path = env->GetStringUTFChars(detectorPath, JNI_FALSE);

    // Profiler.
    Profiler profiler;
    profiler.start();

    // We need a shape_predictor. This is the tool that will predict face
    // landmark positions given an image and face bounding box.  Here we are just
    // loading the model from the shape_predictor_68_face_landmarks.dat file you gave
    // as a command line argument.
    // Deserialize the shape detector.
    dlib::deserialize(path) >> sFaceLandmarksPredictor;

    double interval = profiler.stopAndGetInterval();

    LOGI("L%d: sFaceLandmarksPredictor is initialized (took %.3f ms)", __LINE__, interval);
    LOGI("L%d: sFaceLandmarksPredictor.num_parts()=%lu", __LINE__, sFaceLandmarksPredictor.num_parts());

    env->ReleaseStringUTFChars(detectorPath, path);
}

extern "C" JNIEXPORT jbyteArray JNICALL
JNI_METHOD(detectFacesAndLandmarks)(JNIEnv *env,
                                    jobject thiz,
                                    jobject bitmap) {
    if (sFaceDetector.num_detectors() == 0) {
        LOGI("L%d: sFaceDetector is not initialized!", __LINE__);
        throwException(env, "sFaceDetector is not initialized!");
        return NULL;
    }
    if (sFaceLandmarksPredictor.num_parts() == 0) {
        LOGI("L%d: sFaceLandmarksPredictor is not initialized!", __LINE__);
        throwException(env, "sFaceLandmarksPredictor is not initialized!");
        return NULL;
    }

    // Profiler.
    Profiler profiler;
    profiler.start();

    // Convert bitmap to dlib::array2d.
    dlib::array2d<dlib::rgb_pixel> img;
    convertBitmapToArray2d(env, bitmap, img);

    double interval = profiler.stopAndGetInterval();

    const float width = (float) img.nc();
    const float height = (float) img.nr();
    LOGI("L%d: input image (w=%f, h=%f) is read (took %.3f ms)",
         __LINE__, width, height, interval);

//    // Make the image larger so we can detect small faces.
//    dlib::pyramid_up(img);
//    LOGI("L%d: pyramid_up the input image (w=%lu, h=%lu).", __LINE__, img.nc(), img.nr());

    profiler.start();

    // Now tell the face detector to give us a list of bounding boxes
    // around all the faces in the image.
    std::vector<dlib::rectangle> dets = sFaceDetector(img);
    interval = profiler.stopAndGetInterval();
    LOGI("L%d: Number of faces detected: %lu (took %.3f ms)",
         __LINE__, dets.size(), interval);

    // Protobuf message.
    FaceList faces;
    // Now we will go ask the shape_predictor to tell us the pose of
    // each face we detected.
    std::vector<dlib::full_object_detection> shapes;
    for (unsigned long j = 0; j < dets.size(); ++j) {
        profiler.start();
        dlib::full_object_detection shape = sFaceLandmarksPredictor(img, dets[j]);
        interval = profiler.stopAndGetInterval();
        LOGI("L%d: #%lu face, %lu landmarks detected (took %.3f ms)",
             __LINE__, j, shape.num_parts(), interval);

        profiler.start();
        // Protobuf message.
        Face* face = faces.add_faces();
        // You get the idea, you can get all the face part locations if
        // you want them.  Here we just store them in shapes so we can
        // put them on the screen.
        for (u_long i = 0 ; i < shape.num_parts(); ++i) {
            dlib::point& pt = shape.part(i);

            Landmark* landmark = face->add_landmarks();
            landmark->set_x((float) pt.x() / width);
            landmark->set_y((float) pt.y() / height);
//            LOGI("L%d: point #%lu (x=%f,y=%f)",
//                 __LINE__, i, landmark->x(), landmark->y());
        }
        interval = profiler.stopAndGetInterval();
        LOGI("L%d: Convert #%lu face to protobuf message (took %.3f ms)",
             __LINE__, j, interval);

        shapes.push_back(shape);
    }

    profiler.start();

    // Prepare the return message.
    int outSize = faces.ByteSize();
    jbyteArray out = env->NewByteArray(outSize);
    jbyte* buffer = new jbyte[outSize];

    faces.SerializeToArray(buffer, outSize);
    env->SetByteArrayRegion(out, 0, outSize, buffer);
    delete[] buffer;

    interval = profiler.stopAndGetInterval();
    LOGI("L%d: Convert faces to protobuf message (took %.3f ms)",
         __LINE__, interval);

    return out;
}
