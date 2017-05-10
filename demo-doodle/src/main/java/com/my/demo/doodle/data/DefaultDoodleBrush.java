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

package com.my.demo.doodle.data;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.Log;

import com.my.demo.doodle.protocol.ISketchStroke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DefaultDoodleBrush implements ISketchStroke {

    // Config.
    private final float mMinDistance;
    private final long mMinTimeDuration;

    // State.
    private long mPrevAddTime;
    private final Paint mStrokePaint;
    private final List<PathTuple> mPathTuples = new ArrayList<>();

    // TODO: Refactor this by using build pattern.
    public DefaultDoodleBrush(final float minDistanceThreshold,
                              final long minTimeDurationThreshold) {
        mMinDistance = minDistanceThreshold;
        mMinTimeDuration = minTimeDurationThreshold;
        Log.d("xyz", "mMinDistance=" + mMinDistance);
        Log.d("xyz", "mMinTimeDuration=" + mMinTimeDuration);

        mStrokePaint = new Paint();
        mStrokePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public boolean savePathTuple(float x,
                                 float y,
                                 float width,
                                 int color) {
        mStrokePaint.setStrokeWidth(width);
        mStrokePaint.setColor(color);

        // TODO: Determine whether to record the tuple by distance and time
        // TODO: compared to the previous tuple.
        return canAdd(x, y) &&
               mPathTuples.add(new DefaultPathTuple(x, y));
    }

    @Override
    public PathTuple getPathTupleAt(int position) {
        return mPathTuples.get(position);
    }

    @Override
    public List<PathTuple> getAllPathTuples() {
        return mPathTuples;
    }

    @Override
    public void draw(Canvas canvas) {
        draw(canvas, 0, getAllPathTuples().size() - 1);
    }

    @Override
    public void draw(Canvas canvas,
                     int start,
                     int end) {
        for (int i = start + 1; i <= end; ++i) {
            final Anchor a0 = mPathTuples.get(i - 1).getAnchorAt(0);
            final Anchor a1 = mPathTuples.get(i).getAnchorAt(0);

            canvas.drawLine(a0.getX(), a0.getY(), a1.getX(), a1.getY(), mStrokePaint);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private boolean canAdd(final float x,
                           final float y) {
        if (mPathTuples.size() == 0) return true;

        final long duration = SystemClock.currentThreadTimeMillis() - mPrevAddTime;
        final PathTuple tuple = mPathTuples.get(mPathTuples.size() - 1);
        final Anchor anchor = tuple.getAnchorAt(0);

        Log.d("xyz", "duration=" + duration + ", " +
                     "distance=" + Math.hypot(x - anchor.getX(), y - anchor.getY()));
        if (duration > mMinTimeDuration &&
            Math.hypot(x - anchor.getX(), y - anchor.getY()) > mMinDistance) {

            mPrevAddTime = SystemClock.currentThreadTimeMillis();

            return true;
        } else {
            return false;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private static class DefaultPathTuple implements ISketchStroke.PathTuple {

        private final Anchor mSingleAnchor;

        private DefaultPathTuple(final float x,
                                 final float y) {
            mSingleAnchor = new DefaultAnchor().setX(x)
                                               .setY(y);
        }

        @Override
        public Anchor getAnchorAt(int position) {
            return mSingleAnchor;
        }

        @Override
        public List<Anchor> getAllAnchors() {
            return Collections.singletonList(mSingleAnchor);
        }
    }

    private static class DefaultAnchor implements ISketchStroke.Anchor {

        private float mX;
        private float mY;
        private float mWidth;
        private int mColor;

        @Override
        public Anchor setX(float x) {
            mX = x;
            return this;
        }

        @Override
        public float getX() {
            return mX;
        }

        @Override
        public Anchor setY(float y) {
            mY = y;
            return this;
        }

        @Override
        public float getY() {
            return mY;
        }

        @Override
        public Anchor setColor(int color) {
            mColor = color;
            return this;
        }

        @Override
        public int getColor() {
            return mColor;
        }

        @Override
        public Anchor setWidth(float width) {
            mWidth = width;
            return this;
        }

        @Override
        public float getWidth() {
            return mWidth;
        }
    }
}
