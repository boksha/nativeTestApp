package com.example.miodragmilosevic.nativetest.filters.javaFilters;

import android.graphics.Color;

import com.example.miodragmilosevic.nativetest.filters.ImageFilter;

import java.util.Random;

/**
 * Created by miodrag.milosevic on 12/15/2017.
 */

public class NoiseFilter extends ImageFilter {
    private static final int COLOR_MAX = 255;

    @Override
    protected int[] transformPixels(int[] pixelArray, int imageWidth, int imageHeight) {
        Random random = new Random();

        // iteration through pixels
        for (int i = 0; i < pixelArray.length; i++) {
            int randColor = Color.rgb(random.nextInt(COLOR_MAX),
                    random.nextInt(COLOR_MAX), random.nextInt(COLOR_MAX));
            // OR
            pixelArray[i] |= randColor;
        }
        return pixelArray;
    }
}
