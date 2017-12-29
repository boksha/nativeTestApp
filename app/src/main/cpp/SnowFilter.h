//
// Created by miodrag.milosevic on 12/29/2017.
//

#ifndef NATIVETEST_SNOWFILTER_H
#define NATIVETEST_SNOWFILTER_H


#include <jni.h>
#include "ImageFilter.h"

class SnowFilter :public ImageFilter{
public:
    const jint COLOR_MAX = 255;
    void transformPixels(jint* pixelArray, jint imageWidth, jint imageHeight);
};


#endif //NATIVETEST_SNOWFILTER_H
