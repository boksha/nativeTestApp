package com.example.miodragmilosevic.nativetest.filters.javaFilters;

import android.graphics.Color;

import com.example.miodragmilosevic.nativetest.filters.ImageFilter;

/**
 * Created by miodrag.milosevic on 12/15/2017.
 */

public class TvFilter extends ImageFilter {

    public static final int GAP = 4;

    @Override
    protected int[] transformPixels(int[] pixelArray, int imageWidth, int imageHeight) {
        int R, G, B;
        int A;

        for (int x = 0; x < imageWidth; x++) {
            for (int y = 0; y < imageHeight; y += GAP) {
                A = R = G = B = 0;

                for (int w = 0; w < GAP; w++) {
                    int index = (y + w) * imageWidth + x;
                    if (index < imageWidth * imageHeight) {
                        //median value
                        A = Color.alpha(pixelArray[index]);
                        R +=Color.red(pixelArray[index]);
                        G += Color.green(pixelArray[index]);
                        B += Color.blue(pixelArray[index]);

                    }
                }
//                Log.i(TAG, "tvFilter:R " + R + " G " + G + " B " + B);
                for (int w = 0; w < GAP; w++) {
                    int index = (y + w) * imageWidth + x;
                    if (index < imageWidth * imageHeight) {
                        if (w == 0) {
                            pixelArray[(y + w) * imageWidth + x] = Color.argb(A,R/ GAP, 0, 0);
                        }
                        if (w == 1) {
                            pixelArray[(y + w) * imageWidth + x] =  Color.argb(A,0, G/ GAP, 0);
                        }
                        if (w == 2) {
                            pixelArray[(y + w) * imageWidth + x] = Color.argb(A,0, 0, B/ GAP);
                        }
//					if (w == 3) {
//						pixels[(y + w) * width + x] =Color.argb(A,255, 255, 255);
//					}
                    }
                }
            }
        }
        return pixelArray;
    }
}
