//
// Created by miodrag.milosevic on 12/29/2017.
//

#include <stdlib.h>
#include "SnowFilter.h"
#include "color.h"


void SnowFilter::transformPixels(jint *pixelArray, jint imageWidth, jint imageHeight) {
    jint  threshHold = 0;
// iteration through pixels
// Get original pixels

    jint A, R, G, B;
    for (jint i = 0; i < imageWidth*imageHeight; i++) {
        A = Color::alpha(pixelArray[i]);
        R = Color::red(pixelArray[i]) ;
        G = Color::green(pixelArray[i] );
        B = Color::blue(pixelArray[i]) ;
        threshHold = rand()%COLOR_MAX;

        if(R > threshHold && G > threshHold && B > threshHold) {
            pixelArray[i] = Color::argb(A,COLOR_MAX, COLOR_MAX, COLOR_MAX);
        } else {
            pixelArray[i] = Color::argb(A, R, G, B);
        }
    }
}
