package com.example.miodragmilosevic.nativetest.filters.nativefilters;

import com.example.miodragmilosevic.nativetest.filters.ImageFilter;
import com.example.miodragmilosevic.nativetest.filters.ImageProcessor;

import static com.example.miodragmilosevic.nativetest.filters.ImageProcessor.GRAYSCALE;
import static com.example.miodragmilosevic.nativetest.filters.ImageProcessor.NEGATIVE;
import static com.example.miodragmilosevic.nativetest.filters.ImageProcessor.NOISE;
import static com.example.miodragmilosevic.nativetest.filters.ImageProcessor.OLD_TV;
import static com.example.miodragmilosevic.nativetest.filters.ImageProcessor.ONE_COLOR;
import static com.example.miodragmilosevic.nativetest.filters.ImageProcessor.PIXELATE;
import static com.example.miodragmilosevic.nativetest.filters.ImageProcessor.SNOW;

/**
 * Created by miodrag.milosevic on 12/28/2017.
 */

public class NativeFilterFactory {

    public static ImageFilter getFilter(@ImageProcessor.Type int type){
        if (type == GRAYSCALE) {
            return new GrayScaleFilter();
        } else if (type == NEGATIVE) {
            return new NegativeFilter();
        }
        else if (type == NOISE) {
            return new NoiseFilter();
        } else if (type == ONE_COLOR) {
            return new OneColorFilter();
        } else if (type == SNOW) {
            return new SnowFilter();
        } else if (type == OLD_TV) {
            return new TvFilter();
        } else if (type == PIXELATE) {
            return new PixelateFilter();
        } else {
            return new OriginalFilter();
        }
    }
}