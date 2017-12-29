//
// Created by miodrag.milosevic on 12/29/2017.
//

#ifndef NATIVETEST_PIXELATEFILTER_H
#define NATIVETEST_PIXELATEFILTER_H


#include <jni.h>
#include "ImageFilter.h"

class PixelateFilter :public ImageFilter {
public:
    void transformPixels(jint* pixelArray, jint imageWidth, jint imageHeight);
};


#endif //NATIVETEST_PIXELATEFILTER_H
