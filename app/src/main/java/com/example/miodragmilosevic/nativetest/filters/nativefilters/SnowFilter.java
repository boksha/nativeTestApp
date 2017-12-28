package com.example.miodragmilosevic.nativetest.filters.nativefilters;

import com.example.miodragmilosevic.nativetest.NativeHelper;
import com.example.miodragmilosevic.nativetest.filters.ImageFilter;

/**
 * Created by miodrag.milosevic on 12/28/2017.
 */

class SnowFilter extends ImageFilter {
    @Override
    protected int[] transformPixels(int[] pixelArray, int imageWidth, int imageHeight) {
        return NativeHelper.getInstance().snowFilter(pixelArray,imageWidth,imageHeight);
    }
}
