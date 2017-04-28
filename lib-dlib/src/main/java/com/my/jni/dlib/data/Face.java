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

package com.my.jni.dlib.data;

import java.util.ArrayList;
import java.util.List;

public class Face {

    private final List<Landmark> mLandmarks = new ArrayList<>();

    public Face(Messages.Face rawFace) {
        for (int i = 0; i < rawFace.getLandmarksCount(); ++i) {
            Messages.Landmark rawLandmark = rawFace.getLandmarks(i);

            mLandmarks.add(new Landmark(rawLandmark.getX(),
                                        rawLandmark.getY()));
        }
    }

    public List<Landmark> getAllLandmarks() {
        return mLandmarks;
    }

    // TODO: Figure out what points are eyebrows, nose, mouth and chin.

    public List<Landmark> getEyebrowsLandmarks() {
        return null;
    }

    public List<Landmark> getNoseLandmarks() {
        return null;
    }

    public List<Landmark> getMouthLandmarks() {
        return null;
    }

    public List<Landmark> getChinLandmarks() {
        return null;
    }

    @Override
    public String toString() {
        return "Face{" +
               "mLandmarks=" + mLandmarks +
               '}';
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    public static class Landmark {

        public final float x;
        public final float y;

        public Landmark(float x,
                        float y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Landmark{" +
                   "x=" + x +
                   ", y=" + y +
                   '}';
        }
    }
}
