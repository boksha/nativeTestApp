package com.example.miodragmilosevic.nativetest.filters.javaFilters;

import android.media.Image;

import com.example.miodragmilosevic.nativetest.filters.ImageFilter;

/**
 * Created by miodrag.milosevic on 12/15/2017.
 */

public class OriginalFilter extends ImageFilter {
    @Override
    protected int[] transformPixels(int[] pixelArray, int imageWidth, int imageHeight) {
        return pixelArray;
    }
}
