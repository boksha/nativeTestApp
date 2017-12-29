#include <jni.h>
#include <string>
#include <math.h>
#include "color.h"
#include <stdlib.h>
#include "GrayScaleFilter.h"
#include "PixelateFilter.h"
#include "NegativeFilter.h"
#include "NoiseFilter.h"
#include "OneColorFilter.h"
#include "SnowFilter.h"
#include "TvFilter.h"

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_calculateArea(
        JNIEnv *jenv,
        jobject /* this */,
        jdouble radius
) {
    jdouble area = M_PI * radius * radius *2;
    char output[40];
    sprintf(output, "The area is %f sqm", area);
    return jenv->NewStringUTF(output);
}
extern "C"
JNIEXPORT
jfloat
JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_getMemberFieldFromNative(
        JNIEnv *env,
        jobject callingObject,
        jobject obj) //obj is the MeshData java object passed
{
    float result = 10.0f;

    //Get the passed object's class
    jclass cls = env->GetObjectClass(obj);

    // get field [F = Array of float
    jfieldID fieldId = env->GetFieldID(cls, "VertexCoords", "[F");

    // Get the object field, returns JObject (because it’s an Array)
    jobject objArray = env->GetObjectField (obj, fieldId);

    // Cast it to a jfloatarray
    jfloatArray* fArray = reinterpret_cast<jfloatArray*>(&objArray);

    jsize len = env->GetArrayLength(*fArray);

    // Get the elements
    float* data = env->GetFloatArrayElements(*fArray, 0);

    for(int i=0; i<len; ++i)
    {
        result += data[i];
    }

    // Don't forget to release it
    env->ReleaseFloatArrayElements(*fArray, data, 0);

    return result;
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_grayScaleFilter(JNIEnv *env,
                                                                          jobject instance,
                                                                          jintArray pixelArray_,
                                                                          jint width, jint height) {
    jint *pixelArray = env->GetIntArrayElements(pixelArray_, NULL);
    GrayScaleFilter filter;
    filter.transformPixels(pixelArray,width,height);
    env->ReleaseIntArrayElements(pixelArray_, pixelArray, 0);
    return pixelArray_;
}
extern "C"
JNIEXPORT jintArray JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_pixelateFilter(JNIEnv *env,
                                                                         jobject instance,
                                                                         jintArray pixels_,
                                                                         jint width, jint height,
                                                                         jint pixelSize) {
    jint *pixels = env->GetIntArrayElements(pixels_, NULL);
    PixelateFilter filter;
    filter.transformPixels(pixels,width,height);
    env->ReleaseIntArrayElements(pixels_, pixels, 0);
    return pixels_;
}
extern "C"
JNIEXPORT jintArray JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_negativeFilter(JNIEnv *env,
                                                                         jobject instance,
                                                                         jintArray pixelArray_,
                                                                         jint imageWidth,
                                                                         jint imageHeight) {
    jint *pixelArray = env->GetIntArrayElements(pixelArray_, NULL);
    NegativeFilter filter;
    filter.transformPixels(pixelArray,imageWidth,imageHeight);
    env->ReleaseIntArrayElements(pixelArray_, pixelArray, 0);
    return pixelArray_;
}
extern "C"
JNIEXPORT jintArray JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_noiseFilter(JNIEnv *env, jobject instance,
                                                                      jintArray pixelArray_,
                                                                      jint imageWidth,
                                                                      jint imageHeight) {
    jint *pixelArray = env->GetIntArrayElements(pixelArray_, NULL);
    NoiseFilter filter;
    filter.transformPixels(pixelArray,imageWidth,imageHeight);
    env->ReleaseIntArrayElements(pixelArray_, pixelArray, 0);
    return pixelArray_;
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_oneColorFilter(JNIEnv *env,
                                                                         jobject instance,
                                                                         jintArray pixelArray_,
                                                                         jint imageWidth,
                                                                         jint imageHeight) {
    jint *pixelArray = env->GetIntArrayElements(pixelArray_, NULL);
    OneColorFilter filter;
    filter.transformPixels(pixelArray,imageWidth,imageHeight);
    env->ReleaseIntArrayElements(pixelArray_, pixelArray, 0);
    return pixelArray_;

}
extern "C"
JNIEXPORT jintArray JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_snowFilter(JNIEnv *env, jobject instance,
                                                                     jintArray pixelArray_,
                                                                     jint imageWidth,
                                                                     jint imageHeight) {
    jint *pixelArray = env->GetIntArrayElements(pixelArray_, NULL);
    SnowFilter filter;
    filter.transformPixels(pixelArray,imageWidth,imageHeight);
    env->ReleaseIntArrayElements(pixelArray_, pixelArray, 0);
    return pixelArray_;

}
extern "C"
JNIEXPORT jintArray JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_tvFilter(JNIEnv *env, jobject instance,
                                                                   jintArray pixelArray_,
                                                                   jint imageWidth,
                                                                   jint imageHeight) {
    jint *pixelArray = env->GetIntArrayElements(pixelArray_, NULL);
    TvFilter filter;
    filter.transformPixels(pixelArray,imageWidth,imageHeight);
    env->ReleaseIntArrayElements(pixelArray_, pixelArray, 0);
    return pixelArray_;

}