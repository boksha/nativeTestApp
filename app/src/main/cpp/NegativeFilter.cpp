//
// Created by miodrag.milosevic on 12/29/2017.
//

#include "NegativeFilter.h"

void NegativeFilter::transformPixels(jint *pixelArray, jint imageWidth, jint imageHeight) {
    for (int i = 0; i < imageHeight*imageWidth; i++) {
        pixelArray[i] ^= RGB_MASK;
    }
}
