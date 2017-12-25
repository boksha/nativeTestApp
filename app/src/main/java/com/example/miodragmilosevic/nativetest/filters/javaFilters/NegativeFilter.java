package com.example.miodragmilosevic.nativetest.filters.javaFilters;

import com.example.miodragmilosevic.nativetest.filters.ImageFilter;

/**
 * Created by miodrag.milosevic on 12/15/2017.
 */

public class NegativeFilter extends ImageFilter {

    private static final int RGB_MASK = 0x00FFFFFF;

    @Override
    protected int[] transformPixels(int[] pixelArray, int imageWidth, int imageHeight) {
        int numOfPixels = imageWidth*imageHeight;
        for (int i = 0; i < numOfPixels; i++) {
            pixelArray[i] ^= RGB_MASK;
        }
        return pixelArray;
    }
}
