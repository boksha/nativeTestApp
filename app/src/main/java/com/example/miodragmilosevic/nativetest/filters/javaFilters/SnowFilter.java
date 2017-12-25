package com.example.miodragmilosevic.nativetest.filters.javaFilters;

import android.graphics.Color;

import com.example.miodragmilosevic.nativetest.filters.ImageFilter;

import java.util.Random;

/**
 * Created by miodrag.milosevic on 12/15/2017.
 */

public class SnowFilter extends ImageFilter {
    private static final int COLOR_MAX = 255;

    @Override
    protected int[] transformPixels(int[] pixelArray, int imageWidth, int imageHeight) {
        Random random = new Random();

        int  threshHold = 0;
        // iteration through pixels
        // Get original pixels

        int A, R, G, B;
        for (int i = 0; i < pixelArray.length; i++) {
            A = Color.alpha(pixelArray[i]);
            R = Color.red(pixelArray[i]) ;
            G = Color.green(pixelArray[i] );
            B = Color.blue(pixelArray[i]) ;
            threshHold = random.nextInt(COLOR_MAX);

            if(R > threshHold && G > threshHold && B > threshHold) {
                pixelArray[i] = Color.argb(A,COLOR_MAX, COLOR_MAX, COLOR_MAX);
            } else {
                pixelArray[i] = Color.argb(A, R, G, B);
            }
        }
        return pixelArray;
    }
}
