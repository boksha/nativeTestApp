package com.example.miodragmilosevic.nativetest.filters;

import android.graphics.Bitmap;

import com.example.miodragmilosevic.nativetest.filters.nativefilters.NativeFilterFactory;

/**
 * Created by miodrag.milosevic on 12/28/2017.
 */

public class NativeImageProcessor implements ImageProcessor {
    @Override
    public Bitmap applyFilter(Bitmap originalBitmap, int type) {
        return NativeFilterFactory.getFilter(type).applyFilter(originalBitmap);
    }
}
