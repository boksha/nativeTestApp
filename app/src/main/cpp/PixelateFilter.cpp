//
// Created by miodrag.milosevic on 12/29/2017.
//

#include "PixelateFilter.h"
#include "color.h"

void PixelateFilter::transformPixels(jint *pixelArray, jint width, jint height) {
    jint index = 0;
    jint pixelSize = 10;
// iteration through pixels
    for (jint i = 0; i < width; i += pixelSize) {
        for (jint j = 0; j < height; j += pixelSize) {
            jint a = 0, r = 0, g = 0, b = 0;
            jint sum = pixelSize * pixelSize;
            for (jint x = i; x < i + pixelSize; x++) {


                for (jint y = j; y < j + pixelSize; y++) {
                    index = x + y * width;
                    if (index < width * height) {
                        a = Color::alpha(pixelArray[index]);
                        r += Color::red(pixelArray[index]);
                        g += Color::green(pixelArray[index]);
                        b += Color::blue(pixelArray[index]);
                    }
                }
            }
            for (jint x = i; x < i + pixelSize; x++) {
                for (jint y = j; y < j + pixelSize; y++) {
                    index = x + y * width;
                    if (x < width && y < height && index < width * height) {
                        pixelArray[x + y * width] = Color::argb(a,r/sum, g/sum, b/sum);
                    }

                }
            }
        }
    }
}
