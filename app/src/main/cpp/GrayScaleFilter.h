//
// Created by miodrag.milosevic on 12/29/2017.
//

#ifndef NATIVETEST_GRAYSCALEFILTER_H
#define NATIVETEST_GRAYSCALEFILTER_H

#include <jni.h>
#include "color.h"
#include "ImageFilter.h"

class GrayScaleFilter : public ImageFilter{
public:
    const jdouble GS_RED = 0.2126;
    const jdouble GS_GREEN = 0.7152;
    const jdouble GS_BLUE = 0.0722;
    void transformPixels(jint* pixelArray, jint imageWidth, jint imageHeight);
};


#endif //NATIVETEST_GRAYSCALEFILTER_H
