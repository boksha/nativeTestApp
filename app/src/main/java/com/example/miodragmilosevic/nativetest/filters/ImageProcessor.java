package com.example.miodragmilosevic.nativetest.filters;

import android.graphics.Bitmap;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import io.reactivex.Single;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by miodrag.milosevic on 12/14/2017.
 */

public interface ImageProcessor {
    @Retention(SOURCE)
    @IntDef({ORIGINAL, GRAYSCALE, NEGATIVE, NOISE, RELIEF, OLD_TV, PIXELATE, ONE_COLOR, SNOW})
    @interface Type {
    }

    int ORIGINAL = 0;
    int GRAYSCALE = 1;
    int NEGATIVE = 2;
    int NOISE = 3;
    int RELIEF = 4;
    int OLD_TV = 5;
    int PIXELATE = 6;
    int ONE_COLOR = 7;
    int SNOW = 8;

    Bitmap applyFilter(Bitmap originalBitmap,@Type int type);
}
