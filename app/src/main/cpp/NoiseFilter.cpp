//
// Created by miodrag.milosevic on 12/29/2017.
//

#include <stdlib.h>
#include "NoiseFilter.h"
#include "color.h"

void NoiseFilter::transformPixels(int *pixelArray, int imageWidth, int imageHeight) {

    // iteration through pixels
    for (jint i = 0; i < imageWidth*imageHeight; i++) {
        int randColor = Color::rgb(rand()%COLOR_MAX,
                                   rand()%COLOR_MAX, rand()%COLOR_MAX);
        // OR
        pixelArray[i] |= randColor;
    }
}
