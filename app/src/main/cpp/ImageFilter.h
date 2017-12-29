//
// Created by miodrag.milosevic on 12/29/2017.
//

#ifndef NATIVETEST_IMAGEFILTER_H
#define NATIVETEST_IMAGEFILTER_H


#include <jni.h>

class ImageFilter {
public:
    virtual  void transformPixels(jint* pixelArray, jint imageWidth, jint imageHeight) =0;;
};

#endif //NATIVETEST_IMAGEFILTER_H
