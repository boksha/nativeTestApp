//
// Created by miodrag.milosevic on 12/29/2017.
//

#ifndef NATIVETEST_TVFILTER_H
#define NATIVETEST_TVFILTER_H


#include <jni.h>
#include "ImageFilter.h"

class TvFilter :public ImageFilter{
public:
    const jint GAP = 4;
    void transformPixels(jint* pixelArray, jint imageWidth, jint imageHeight);
};


#endif //NATIVETEST_TVFILTER_H
