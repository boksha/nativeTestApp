package com.example.miodragmilosevic.nativetest.filters;

import android.graphics.Bitmap;

import com.example.miodragmilosevic.nativetest.filters.javaFilters.JavaFilterFactory;

import io.reactivex.Single;

/**
 * Created by miodrag.milosevic on 12/14/2017.
 */

public class JavaImageProcessor implements ImageProcessor {

    @Override
    public Single<Bitmap> applyFilter(Bitmap source, @Type int type) {
        return Single.fromCallable(() -> JavaFilterFactory.getFilter(type).applyFilter(source)
        );
    }
}
