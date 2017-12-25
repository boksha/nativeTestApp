package com.example.miodragmilosevic.nativetest.filters.javaFilters;

import android.graphics.Color;

import com.example.miodragmilosevic.nativetest.filters.ImageFilter;

/**
 * Created by miodrag.milosevic on 12/15/2017.
 */

public class PixelateFilter extends ImageFilter {

    private static final int PIXEL_SIZE = 10;

    @Override
    protected int[] transformPixels(int[] pixelArray, int imageWidth, int imageHeight) {
        int index = 0;
        // iteration through pixels
        for (int i = 0; i < imageWidth; i += PIXEL_SIZE) {
            for (int j = 0; j < imageHeight; j += PIXEL_SIZE) {
                int a = 0, r = 0, g = 0, b = 0;
                int sum = PIXEL_SIZE * PIXEL_SIZE;
                for (int x = i; x < i + PIXEL_SIZE; x++) {


                    for (int y = j; y < j + PIXEL_SIZE; y++) {
                        index = x + y * imageWidth;
                        if (index < imageWidth * imageHeight) {
                            a = Color.alpha(pixelArray[index]);
                            r += Color.red(pixelArray[index]);
                            g += Color.green(pixelArray[index]);
                            b += Color.blue(pixelArray[index]);
                        }
                    }
                }
                for (int x = i; x < i + PIXEL_SIZE; x++) {
                    for (int y = j; y < j + PIXEL_SIZE; y++) {
                        index = x + y * imageWidth;
                        if (x < imageWidth && y < imageHeight && index < imageWidth * imageHeight) {
                            pixelArray[x + y * imageWidth] = Color.argb(a,r/sum, g/sum, b/sum);
                        }

                    }
                }
            }
        }
        return pixelArray;
    }
}
