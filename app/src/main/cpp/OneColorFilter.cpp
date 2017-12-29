//
// Created by miodrag.milosevic on 12/29/2017.
//

#include <stdlib.h>
#include "OneColorFilter.h"
#include "color.h"


void OneColorFilter::transformPixels(jint *pixelArray, jint imageWidth, jint imageHeight) {
    jint red = 0;
    jint green = 0;
    jint blue = 0;
    jint switchValue = rand() % 3;
    if (switchValue == 0) {
        red = 1;
    } else if (switchValue == 1) {
        green = 1;
    } else {
        blue = 1;
    }
    jint A, R, G, B;
    for (jint i = 0; i < imageHeight*imageWidth; i++) {
        A = Color::alpha(pixelArray[i]);
        R = Color::red(pixelArray[i]) * red;
        G = Color::green(pixelArray[i] * green);
        B = Color::blue(pixelArray[i]) * blue;
        pixelArray[i] = Color::argb(A, R, G, B);
    }
}
