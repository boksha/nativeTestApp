package com.example.miodragmilosevic.nativetest;

/**
 * Created by miodrag.milosevic on 11/24/2017.
 */

public class NativeHelper {


    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("nativetest");
    }

    private static NativeHelper sInstance = new NativeHelper();

    public static NativeHelper getInstance(){
        return sInstance;
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native String calculateArea(double radius);
    public native float getMemberFieldFromNative(MashData obj);

    public native int[] grayScaleFilter(int[] pixelArray, int width, int height);

    public native int[] pixelateFilter(int[] pixels, int width, int height, int pixelSize);

    public native int[] negativeFilter(int[] pixelArray, int imageWidth, int imageHeight);

    public native int[] noiseFilter(int[] pixelArray, int imageWidth, int imageHeight);

    public native int[] oneColorFilter(int[] pixelArray, int imageWidth, int imageHeight);

    public native int[] snowFilter(int[] pixelArray, int imageWidth, int imageHeight);

    public native int[] tvFilter(int[] pixelArray, int imageWidth, int imageHeight);
/////////////////

//    public static native int convertGray(long mAddrRgba,  long mAddrGray);
    public static native int detectEdges(long mAddrInput,  long mAddrOutput);
    public static native void cartoonify(long mAddrRgba,  long mAddrGray);

    public static native void findFeatures(long nativeObjAddr, long nativeObjAddr1);
}
