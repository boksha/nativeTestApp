//
// Created by miodrag.milosevic on 12/29/2017.
//

#ifndef NATIVETEST_NOISEFILTER_H
#define NATIVETEST_NOISEFILTER_H

#include <jni.h>
#include "ImageFilter.h"

class NoiseFilter :public ImageFilter {
public:
    const jint COLOR_MAX = 255;
    void transformPixels(jint* pixelArray, jint imageWidth, jint imageHeight);
};

#endif //NATIVETEST_NOISEFILTER_H
