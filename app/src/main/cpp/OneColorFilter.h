//
// Created by miodrag.milosevic on 12/29/2017.
//

#ifndef NATIVETEST_ONECOLORFILTER_H
#define NATIVETEST_ONECOLORFILTER_H


#include <jni.h>
#include "ImageFilter.h"

class OneColorFilter :public ImageFilter{
public:
    void transformPixels(jint* pixelArray, jint imageWidth, jint imageHeight);
};


#endif //NATIVETEST_ONECOLORFILTER_H
