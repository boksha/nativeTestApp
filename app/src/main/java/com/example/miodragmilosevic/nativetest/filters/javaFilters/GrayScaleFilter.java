package com.example.miodragmilosevic.nativetest.filters.javaFilters;

import android.graphics.Color;

import com.example.miodragmilosevic.nativetest.filters.ImageFilter;

/**
 * Created by miodrag.milosevic on 12/15/2017.
 */

public class GrayScaleFilter extends ImageFilter {

    private  static final double GS_RED = 0.2126;
    private static final double GS_GREEN = 0.7152;
    private static final double GS_BLUE = 0.0722;

    @Override
    protected int[] transformPixels(int[] pixelArray, int imageWidth, int imageHeight) {

        int A, R, G, B, level;
        for (int i = 0; i < pixelArray.length; i++) {
            A = Color.alpha(pixelArray[i]);
            R = Color.red(pixelArray[i]);
            G = Color.green(pixelArray[i]);
            B = Color.blue(pixelArray[i]);
            level = (int) (R * GS_RED + G * GS_GREEN + B * GS_BLUE);
            pixelArray[i] = Color.argb(A, level, level, level);
        }
        return pixelArray;
    }
}
