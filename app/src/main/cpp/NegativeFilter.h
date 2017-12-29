//
// Created by miodrag.milosevic on 12/29/2017.
//

#ifndef NATIVETEST_NEGATIVEFILTER_H
#define NATIVETEST_NEGATIVEFILTER_H

#include <jni.h>
#include "ImageFilter.h"

class NegativeFilter : public ImageFilter{
public:
    const jint RGB_MASK = 0x00FFFFFF;
    void transformPixels(jint* pixelArray, jint imageWidth, jint imageHeight);
};

#endif //NATIVETEST_NEGATIVEFILTER_H
