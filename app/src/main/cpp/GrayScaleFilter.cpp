//
// Created by miodrag.milosevic on 12/29/2017.
//

#include "GrayScaleFilter.h"

void GrayScaleFilter::transformPixels(jint *pixelArray, jint imageWidth, jint imageHeight) {
    jint A, R, G, B, level;
    for (jint i = 0; i < imageHeight*imageWidth; i++) {
        A = Color::alpha(pixelArray[i]);
        R = Color::red(pixelArray[i]);
        G = Color::green(pixelArray[i]);
        B = Color::blue(pixelArray[i]);
        level = (jint) (R * GS_RED + G * GS_GREEN + B * GS_BLUE);
        pixelArray[i] = Color::argb(A, level, level, level);
    }
}
