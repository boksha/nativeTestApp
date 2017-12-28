package com.example.miodragmilosevic.nativetest.filters;

import android.graphics.Bitmap;

import com.example.miodragmilosevic.nativetest.filters.javaFilters.JavaFilterFactory;

/**
 * Created by miodrag.milosevic on 12/14/2017.
 */

public class JavaImageProcessor implements ImageProcessor {

    @Override
    public Bitmap applyFilter(Bitmap source, @Type int type) {
        return  JavaFilterFactory.getFilter(type).applyFilter(source);
    }
}
