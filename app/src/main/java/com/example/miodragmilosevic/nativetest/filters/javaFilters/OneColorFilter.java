package com.example.miodragmilosevic.nativetest.filters.javaFilters;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.miodragmilosevic.nativetest.filters.ImageFilter;

import java.util.Random;

/**
 * Created by miodrag.milosevic on 12/15/2017.
 */

public class OneColorFilter extends ImageFilter {
    @Override
    protected int[] transformPixels(int[] pixelArray, int imageWidth, int imageHeight) {
        int red = 0;
        int green = 0;
        int blue = 0;
        Random random = new Random();
        int switchValue = random.nextInt(3);
        if (switchValue == 0) {
            red = 1;
        } else if (switchValue == 1) {
            green = 1;
        } else {
            blue = 1;
        }
        int A, R, G, B;
        for (int i = 0; i < pixelArray.length; i++) {
            A = Color.alpha(pixelArray[i]);
            R = Color.red(pixelArray[i]) * red;
            G = Color.green(pixelArray[i] * green);
            B = Color.blue(pixelArray[i]) * blue;
            pixelArray[i] = Color.argb(A, R, G, B);
        }
        return (pixelArray);
    }
}
