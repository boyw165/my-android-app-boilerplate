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

package com.my.demo.dlib;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.cameraview.CameraView;
import com.google.protobuf.InvalidProtocolBufferException;
import com.my.core.protocol.IProgressBarView;
import com.my.core.util.ViewUtil;
import com.my.demo.dlib.util.DlibModelHelper;
import com.my.demo.dlib.view.FaceLandmarksImageView;
import com.my.jni.dlib.FaceLandmarksDetector68;
import com.my.jni.dlib.data.Face;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SampleOfLandmarksOnlyActivity
    extends AppCompatActivity
    implements IProgressBarView,
               Handler.Callback {

//    private static final String ASSET_TEST_PHOTO = "boyw165-i-am-tyson-chandler.jpg";
    private static final String ASSET_TEST_PHOTO = "5-ppl.jpg";
    private static final String ASSET_SHAPE_DETECTOR_DATA = "shape_predictor_68_face_landmarks.dat";

    private static final int MSG_TAKE_PHOTO = 1 << 1;
    private static final int MSG_DETECT_LANDMARKS = 1 << 2;

    // View.
    @BindView(R.id.btn_back)
    FloatingActionButton mBtnBack;
    @BindView(R.id.btn_take_photo)
    FloatingActionButton mBtnTakePhoto;
    @BindView(R.id.face_bound)
    View mFaceBoundView;
    @BindView(R.id.landmarks_preview)
    FaceLandmarksImageView mLandmarksPreview;
    @BindView(R.id.camera)
    CameraView mCameraView;

    // Butter Knife.
    Unbinder mUnbinder;

    // Face Detector.
    Handler mDetectorHandler;
    FaceLandmarksDetector68 mFaceDetector;

    // Data.
    RectF mFaceBound;
    byte[] mData;
    CompositeDisposable mComposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sample_of_landmarks_only);

        // Init view binding.
        mUnbinder = ButterKnife.bind(this);

        // Back button.
        mBtnBack.setOnClickListener(onClickToBack());

        // Camera view.
        mCameraView.addCallback(mCameraCallback);

        // Init the face detector.
        mFaceDetector = new FaceLandmarksDetector68();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mComposition = new CompositeDisposable();
        mComposition.add(
            grantPermission()
                // Show the progress-bar.
                .map(new Function<Boolean, Boolean>() {
                    @Override
                    public Boolean apply(Boolean granted) throws Exception {
                        if (granted) {
                            showProgressBar("Preparing the data...");
                        }
                        return granted;
                    }
                })
                // Face landmarks detection.
                .flatMap(new Function<Boolean, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Boolean granted)
                        throws Exception {
                        if (granted) {
                            return processFaceLandmarksDetection()
                                .subscribeOn(Schedulers.io());
                        } else {
                            return Observable.just(false);
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                // Open the camera.
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object value) throws Exception {
                        // Prepare the capturing rect.
                        mFaceBound = new RectF(
                            (float) mFaceBoundView.getLeft() / mCameraView.getWidth(),
                            (float) mFaceBoundView.getTop() / mCameraView.getHeight(),
                            (float) mFaceBoundView.getRight() / mCameraView.getWidth(),
                            (float) mFaceBoundView.getBottom() / mCameraView.getHeight());

                        // Open the camera.
                        mCameraView.start();

                        return value;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Object>() {
                    @Override
                    public void onNext(Object value) {
                        hideProgressBar();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgressBar();
                    }

                    @Override
                    public void onComplete() {
                        hideProgressBar();
                    }
                }));
    }

    @Override
    protected void onPause() {
        super.onPause();

        hideProgressBar();

        mComposition.clear();

        // Close camera.
        mCameraView.stop();
    }

    @Override
    public void showProgressBar() {
        ViewUtil
            .with(this)
            .setProgressBarCancelable(false)
            .showProgressBar(getString(R.string.loading));
    }

    @Override
    public void showProgressBar(String msg) {
        ViewUtil
            .with(this)
            .setProgressBarCancelable(false)
            .showProgressBar(msg);
    }

    @Override
    public void hideProgressBar() {
        ViewUtil.with(this)
                .hideProgressBar();
    }

    @Override
    public void updateProgress(int progress) {
        ViewUtil.with(this)
                .setProgressBarCancelable(false)
                .showProgressBar(null);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (!mCameraView.isCameraOpened() || isFinishing()) return true;

        switch (msg.what) {
            case MSG_TAKE_PHOTO:
                // Will lead to onPictureTaken callback.
                mCameraView.takePicture();
                return true;
            case MSG_DETECT_LANDMARKS:
                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;

                final Bitmap rawBitmap = BitmapFactory
                    .decodeByteArray(mData, 0, mData.length, options);
                // Optimized bitmap.
                Bitmap optBitmap;

                // FIXME: It's a workaround because I can't get rotation info
                // FIXME: from the CameraView.
                if (mCameraView.getFacing() == CameraView.FACING_FRONT) {
                    final int bw = rawBitmap.getWidth();
                    final int bh = rawBitmap.getHeight();
                    final Matrix matrix = new Matrix();

                    // Flip vertically.
                    matrix.postScale(1, -1, bw / 2, bh / 2);
                    // Adjust the width and height because shape of buffer
                    // doesn't match of the visible shape.
                    final int vw = mCameraView.getWidth();
                    final int vh = mCameraView.getHeight();
                    final float scale = Math.min((float) bw / vw,
                                                 (float) bh / vh);

                    optBitmap = Bitmap.createBitmap(rawBitmap, 0, 0,
                                                    (int) (scale * vw),
                                                    (int) (scale * vh),
                                                    matrix, true);
                } else {
                    optBitmap = Bitmap.createBitmap(rawBitmap, 0, 0,
                                                    rawBitmap.getWidth(),
                                                    rawBitmap.getHeight());
                }

                try {
//                    // Do landmarks detection only
//                    // Call detector JNI.
//                    final int bw = optBitmap.getWidth();
//                    final int bh = optBitmap.getHeight();
//                    final Rect bound = new Rect(
//                        (int) (mFaceBound.left * bw),
//                        (int) (mFaceBound.top * bh),
//                        (int) (mFaceBound.right * bw),
//                        (int) (mFaceBound.bottom * bh));
//                    final List<Face.Landmark> landmarks =
//                        mFaceDetector.findLandmarksInFace(optBitmap, bound);
//
//                    // Display the landmarks.
//                    List<Face> faces = new ArrayList<>();
//                    faces.add(new Face68(landmarks));
//                    if (mLandmarksPreview != null) {
//                        mLandmarksPreview.setFaces(faces);
//                    }

                    // Do face detection and then landmarks detection.
                    // Call detector JNI.
                    final List<Face> faces =
                        mFaceDetector.findFacesAndLandmarks(optBitmap);

                    // Display the faces.
                    if (mLandmarksPreview != null) {
                        mLandmarksPreview.setFaces(faces);
                    }
                } catch (InvalidProtocolBufferException err) {
                    Log.e("xyz", err.getMessage());
                }

                optBitmap.recycle();
                rawBitmap.recycle();

                // Schedule next take-photo.
                if (mCameraView.isCameraOpened()) {
                    mDetectorHandler.sendEmptyMessageDelayed(MSG_TAKE_PHOTO, 200);
                }
                return true;
        }

        return false;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Camera view.
        mCameraView.removeCallback(mCameraCallback);

        // View binding.
        mUnbinder.unbind();
    }

    private CameraView.Callback mCameraCallback =
        new CameraView.Callback() {
            @Override
            public void onCameraOpened(CameraView cameraView) {
                Log.d("xyz", "onCameraOpened");
                // Start a worker thread for detecting face landmarks.
                final HandlerThread worker = new HandlerThread(
                    SampleOfLandmarksOnlyActivity.class.getSimpleName());
                worker.start();
                mDetectorHandler = new Handler(worker.getLooper(),
                                               SampleOfLandmarksOnlyActivity.this);

                // Take photo after a short delay.
                mDetectorHandler.sendEmptyMessageDelayed(MSG_TAKE_PHOTO, 1000);
            }

            @Override
            public void onCameraClosed(CameraView cameraView) {
                Log.d("xyz", "onCameraClosed");
                // Stop the worker thread for detecting face landmarks.
                mDetectorHandler.getLooper().quit();
            }

            @Override
            public void onPictureTaken(CameraView cameraView,
                                       final byte[] data) {
                Log.d("xyz", "onPictureTaken " + data.length + "(" + Looper.myLooper() + ")");

                mData = data;
                mDetectorHandler.sendEmptyMessage(MSG_DETECT_LANDMARKS);
            }
        };

    private View.OnClickListener onClickToBack() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
    }

    private Observable<Boolean> grantPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return RxPermissions
                .getInstance(this)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE,
                         Manifest.permission.WRITE_EXTERNAL_STORAGE,
                         Manifest.permission.CAMERA);
        } else {
            int check1 = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
            int check2 = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int check3 = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA);

            return Observable.just(check1 == PackageManager.PERMISSION_GRANTED &&
                                   check2 == PackageManager.PERMISSION_GRANTED &&
                                   check3 == PackageManager.PERMISSION_GRANTED);
        }
    }

    private Observable<?> processFaceLandmarksDetection() {
        return DlibModelHelper
            .getService()
            .downloadFace68Model(
                getApplicationContext().getPackageName())
            // Update progressbar message.
            .observeOn(AndroidSchedulers.mainThread())
            .map(new Function<File, File>() {
                @Override
                public File apply(File face68ModelPath) throws Exception {
                    showProgressBar("Initializing face detectors...");
                    return face68ModelPath;
                }
            })
            // Deserialize the detector.
            .observeOn(Schedulers.io())
            .map(new Function<File, Boolean>() {
                @Override
                public Boolean apply(File face68ModelPath)
                    throws Exception {
                    if (!mFaceDetector.isFaceDetectorReady()) {
                        mFaceDetector.prepareFaceDetector();
                    }
                    if (!mFaceDetector.isFaceLandmarksDetectorReady()) {
                        mFaceDetector.prepareFaceLandmarksDetector(
                            face68ModelPath.getAbsolutePath());
                    }

                    return true;
                }
            });
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private static class DetectorParams {

        final String shapeDetectorPath;
        final String testPhotoPath;

        DetectorParams(String shapeDetectorPath,
                       String testPhotoPath) {
            this.shapeDetectorPath = shapeDetectorPath;
            this.testPhotoPath = testPhotoPath;
        }
    }
}
