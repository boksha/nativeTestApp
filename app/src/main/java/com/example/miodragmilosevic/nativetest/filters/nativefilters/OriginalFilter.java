package com.example.miodragmilosevic.nativetest.filters.nativefilters;

import com.example.miodragmilosevic.nativetest.filters.ImageFilter;

/**
 * Created by miodrag.milosevic on 12/28/2017.
 */

class OriginalFilter extends ImageFilter {
    @Override
    protected int[] transformPixels(int[] pixelArray, int imageWidth, int imageHeight) {
        return pixelArray;
    }
}
