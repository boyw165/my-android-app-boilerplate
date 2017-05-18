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

package com.my.demo.dlib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.my.jni.dlib.data.Face;
import com.my.jni.dlib.data.Face68;
import com.my.jni.dlib.data.Messages;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FaceLandmarksImageView extends AppCompatImageView {

    private static final float WIDTH = 2.f;
    private final int mWidth;
    private final Paint mPaint;

    private final List<Face> mNormalizedFaces = new CopyOnWriteArrayList<>();
    private final List<Face> mDenormalizedFaces = new CopyOnWriteArrayList<>();

    public FaceLandmarksImageView(Context context) {
        this(context, null);
    }

    public FaceLandmarksImageView(Context context,
                                  AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FaceLandmarksImageView(Context context,
                                  AttributeSet attrs,
                                  int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final float density = getContext()
            .getResources().getDisplayMetrics().density;

        mWidth = (int) (density * WIDTH);
        mPaint = new Paint();
        mPaint.setStrokeWidth(mWidth);
        mPaint.setColor(ContextCompat.getColor(getContext(), com.my.widget.R.color.accent));
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public void setFaces(List<Face> faces) {
//        Log.d("xyz", "drawable bound=" + getDrawable().getBounds());
//        Log.d("xyz", "getImageMatrix()=" + getImageMatrix());

        final Rect bound;
        if (getDrawable() != null) {
            bound = getDrawable().getBounds();
        } else if (getBackground() != null) {
            bound = getBackground().getBounds();
        } else {
            bound = new Rect(getLeft(), getTop(), getRight(), getBottom());
        }
        mNormalizedFaces.clear();
        mNormalizedFaces.addAll(faces);

        // Give the normalized landmarks real dimension.
        mDenormalizedFaces.clear();
        for (int i = 0; i < mNormalizedFaces.size(); ++i) {
            // The normalized face.
            final Face nFace = mNormalizedFaces.get(i);
            // The denormalized face.
            final Face dFace = new Face68(nFace, bound.width(), bound.height());

            mDenormalizedFaces.add(dFace);
        }
        postInvalidate();
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);

        if (!mDenormalizedFaces.isEmpty()) {
            // Render faces.
            canvas.save();
            canvas.concat(getImageMatrix());

            // Render all faces.
            for (int i = 0; i < mDenormalizedFaces.size(); ++i) {
                final Face face = mDenormalizedFaces.get(i);

                // Render face's landmarks.
                for (int j = 0; j < face.getAllLandmarks().size(); ++j) {
                    final Face.Landmark landmark = face.getAllLandmarks().get(j);

                    // Render chin.
                    final List<Face.Landmark> chinMarks = face.getChinLandmarks();
                    for (int k = 1; k < chinMarks.size(); ++k) {
                        final Face.Landmark prev = chinMarks.get(k - 1);
                        final Face.Landmark current = chinMarks.get(k);

                        canvas.drawLine(prev.x, prev.y, current.x, current.y, mPaint);
                    }

                    // Render left eyebrow.
                    final List<Face.Landmark> leftEyebrowMarks = face.getLeftEyebrowLandmarks();
                    for (int k = 1; k < leftEyebrowMarks.size(); ++k) {
                        final Face.Landmark prev = leftEyebrowMarks.get(k - 1);
                        final Face.Landmark current = leftEyebrowMarks.get(k);

                        canvas.drawLine(prev.x, prev.y, current.x, current.y, mPaint);
                    }

                    // Render right eyebrow.
                    final List<Face.Landmark> rightEyebrowMarks = face.getRightEyebrowLandmarks();
                    for (int k = 1; k < rightEyebrowMarks.size(); ++k) {
                        final Face.Landmark prev = rightEyebrowMarks.get(k - 1);
                        final Face.Landmark current = rightEyebrowMarks.get(k);

                        canvas.drawLine(prev.x, prev.y, current.x, current.y, mPaint);
                    }

                    // Render left eye.
                    final List<Face.Landmark> leftEyeMarks = face.getLeftEyeLandmarks();
                    for (int k = 1; k < leftEyeMarks.size(); ++k) {
                        final Face.Landmark prev = leftEyeMarks.get(k - 1);
                        final Face.Landmark current = leftEyeMarks.get(k);

                        canvas.drawLine(prev.x, prev.y, current.x, current.y, mPaint);

                        if (k == leftEyeMarks.size() - 1) {
                            final Face.Landmark first = leftEyeMarks.get(0);
                            canvas.drawLine(current.x, current.y,
                                            first.x, first.y, mPaint);
                        }
                    }

                    // Render right eye.
                    final List<Face.Landmark> rightEyeMarks = face.getRightEyeLandmarks();
                    for (int k = 1; k < rightEyeMarks.size(); ++k) {
                        final Face.Landmark prev = rightEyeMarks.get(k - 1);
                        final Face.Landmark current = rightEyeMarks.get(k);

                        canvas.drawLine(prev.x, prev.y, current.x, current.y, mPaint);

                        if (k == rightEyeMarks.size() - 1) {
                            final Face.Landmark first = rightEyeMarks.get(0);
                            canvas.drawLine(current.x, current.y,
                                            first.x, first.y, mPaint);
                        }
                    }

                    // Render nose.
                    final List<Face.Landmark> noseMarks = face.getNoseLandmarks();
                    for (int k = 1; k < noseMarks.size(); ++k) {
                        final Face.Landmark prev = noseMarks.get(k - 1);
                        final Face.Landmark current = noseMarks.get(k);

                        canvas.drawLine(prev.x, prev.y, current.x, current.y, mPaint);
                    }

                    // Render inner lips.
                    final List<Face.Landmark> innerLipsMarks = face.getInnerLipsLandmarks();
                    for (int k = 1; k < innerLipsMarks.size(); ++k) {
                        final Face.Landmark prev = innerLipsMarks.get(k - 1);
                        final Face.Landmark current = innerLipsMarks.get(k);

                        canvas.drawLine(prev.x, prev.y, current.x, current.y, mPaint);

                        if (k == innerLipsMarks.size() - 1) {
                            final Face.Landmark first = innerLipsMarks.get(0);
                            canvas.drawLine(current.x, current.y,
                                            first.x, first.y, mPaint);
                        }
                    }

                    // Render outer lips.
                    final List<Face.Landmark> outerLipsMarks = face.getOuterLipsLandmarks();
                    for (int k = 1; k < outerLipsMarks.size(); ++k) {
                        final Face.Landmark prev = outerLipsMarks.get(k - 1);
                        final Face.Landmark current = outerLipsMarks.get(k);

                        canvas.drawLine(prev.x, prev.y, current.x, current.y, mPaint);

                        if (k == outerLipsMarks.size() - 1) {
                            final Face.Landmark first = outerLipsMarks.get(0);
                            canvas.drawLine(current.x, current.y,
                                            first.x, first.y, mPaint);
                        }
                    }
                }
            }

            canvas.restore();
        }
    }
}