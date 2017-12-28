package com.example.miodragmilosevic.nativetest.filterimage.mvp;

import android.graphics.Bitmap;

import com.example.miodragmilosevic.nativetest.filters.ImageProcessor;

import io.reactivex.Single;

/**
 * Created by miodrag.milosevic on 12/14/2017.
 */

public class FilterImageRepository implements Contract.Repository {
    ImageProcessor mImageProcessor;

    public FilterImageRepository(ImageProcessor imageprocessor) {
        mImageProcessor = imageprocessor;
    }

    @Override
    public Single<Bitmap> applyFilter(Bitmap originalBitmap, int type) {
        return Single.fromCallable(() -> mImageProcessor.applyFilter(originalBitmap,type));
    }
}
