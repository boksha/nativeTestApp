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
    public native int[] grayScaleFilter(int[] pixelArray);

//    class NativeFilterFunc {
//        public static native int[] lightFilter(int[] pixels, int width, int height, int centerX, int centerY, int radius);
//        public static native int[] lomoAddBlckRound(int[] pixels, int width, int height, double roundRadius);
//        public static native int[] neonFilter(int[] pixels, int width, int height, int r, int g, int b);
//        public static native int[] oilFilter(int[] pixels, int width, int height, int oilRange);
//        public static native int[] tvFilter(int[] pixels, int width, int height);
//        public static native int[] averageSmooth(int[] pixels, int width, int height, int maskSize);
//        public static native int[] hdrFilter(int[] pixels, int width, int height);
//        public static native int[] discreteGaussianBlur(int[] pixels, int width, int height, double sigma);
//        public static native int[] softGlow(int[] pixels, int width, int height, double blurSigma);
//        public static native int[] sketchFilter(int[] pixels, int width, int height);
//        public static native int[] sharpenFilter(int[] pixels, int width, int height);
//        public static native int[] reliefFilter(int[] pixels, int width, int height);
//        public static native int[] pxelateFilter(int[] pixels, int width, int height, int pixelSize);
//        public static native int[] blockFilter(int[] pixels, int width, int height);
//        public static native int[] motionBlurFilter(int[] pixels, int width, int height, int xSpeed, int ySpeed);
//        public static native int[] gothamFilter(int[] pixels, int width, int height);
//    }
}
