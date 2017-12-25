package com.example.miodragmilosevic.nativetest.filters;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Created by miodrag.milosevic on 12/15/2017.
 */

abstract public class ImageFilter {

    public Bitmap applyFilter(Bitmap source){
        // Create mutable Bitmap to invert, argument true makes it mutable
        long startTime = System.currentTimeMillis();
        Bitmap result = source.copy(Bitmap.Config.ARGB_8888, true);

        // Get info about Bitmap
        int width = result.getWidth();
        int height = result.getHeight();
        int pixels = width * height;

        // Get original pixels
        int[] pixelArray = new int[pixels];
        result.getPixels(pixelArray, 0, width, 0, 0, width, height);
        pixelArray = transformPixels(pixelArray,width,height);
        result.setPixels(pixelArray, 0, width, 0, 0, width, height);
        // Return inverted Bitmap
        Log.i("Miki", "applyFilter: consumedTime./ " + (System.currentTimeMillis() - startTime));

        return result;
    }

    protected abstract int[] transformPixels(int[] pixelArray, int imageWidth, int imageHeight);
}
