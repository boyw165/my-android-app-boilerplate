package org.dmlc.mxnet;

import android.graphics.Bitmap;
import android.graphics.Color;

public class Predictor {

    static {
        System.loadLibrary("mxnet_predict");
    }

    private long mHandle = 0;

    public Predictor(byte[] symbol,
                     byte[] params,
                     Device dev,
                     InputNode[] input) {
        String[] keys = new String[input.length];
        int[][] shapes = new int[input.length][];
        for (int i = 0; i < input.length; ++i) {
            keys[i] = input[i].key;
            shapes[i] = input[i].shape;
        }
        mHandle = createPredictor(symbol, params, dev.ctype(), dev.id, keys, shapes);
    }

    public long handle() {
        return mHandle;
    }

    public void free() {
        if (mHandle != 0) {
            nativeFree(mHandle);
            mHandle = 0;
        }
    }

    public float[] getOutput(int index) {
        if (mHandle == 0) return null;
        return nativeGetOutput(mHandle, index);
    }


    public void forward(String key, float[] input) {
        if (mHandle == 0) return;
        nativeForward(mHandle, key, input);
    }

    static public float[] inputFromImage(Bitmap[] bmps,
                                         float meanR,
                                         float meanG,
                                         float meanB) {
        if (bmps.length == 0) return null;

        int width = bmps[0].getWidth();
        int height = bmps[0].getHeight();
        float[] buf = new float[height * width * 3 * bmps.length];
        for (int x = 0; x < bmps.length; x++) {
            Bitmap bmp = bmps[x];
            if (bmp.getWidth() != width || bmp.getHeight() != height)
                return null;

            int[] pixels = new int[height * width];
            bmp.getPixels(pixels, 0, width, 0, 0, height, width);

            int start = width * height * 3 * x;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int pos = i * width + j;
                    int pixel = pixels[pos];
                    buf[start + pos] = Color.red(pixel) - meanR;
                    buf[start + width * height + pos] = Color.green(pixel) - meanG;
                    buf[start + width * height * 2 + pos] = Color.blue(pixel) - meanB;
                }
            }
        }

        return buf;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    /**
     * Create a NN predictor.
     *
     * @param symbol      The graph of the NN, which is usually stored in a JSON
     *                    file. e.g. symbol.json
     * @param params      The pre-trained weights.
     * @param devType     no idea...
     * @param devId       no idea...
     * @param inputKeys   The keys indicating what nodes in the symbol are the
     *                    input nodes.
     * @param inputShapes The dimension (shape) of the input nodes. e.g.
     *                    [[1, 3, 224, 224]]
     *                      ^  ^   ^    ^
     *                      |  |   |    +---------------- Width or height
     *                      |  |   +--------------------- Width or height
     *                      |  +------------------------- Channels.
     *                      +---------------------------- Batch size.
     * @return A handle for referencing the NN.
     */
    private native static long createPredictor(byte[] symbol,
                                               byte[] params,
                                               int devType,
                                               int devId,
                                               String[] inputKeys,
                                               int[][] inputShapes);

    private native static void nativeFree(long handle);

    private native static float[] nativeGetOutput(long handle,
                                                  int index);

    private native static void nativeForward(long handle,
                                             String key,
                                             float[] input);

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    public static class InputNode {
        String key;
        int[] shape;

        public InputNode(String key, int[] shape) {
            this.key = key;
            this.shape = shape;
        }
    }

    public static class Device {
        public enum Type {
            CPU, GPU, CPU_PINNED
        }

        public Device(Type t, int i) {
            this.type = t;
            this.id = i;
        }

        Type type;
        int id;

        int ctype() {
            return this.type == Type.CPU ? 1 : this.type == Type.GPU ? 2 : 3;
        }
    }
}