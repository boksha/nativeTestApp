#include <jni.h>
#include <string>
#include <math.h>
#include "color.h"
#include <stdlib.h>

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
    const jdouble GS_RED = 0.2126;
    const jdouble GS_GREEN = 0.7152;
    const jdouble GS_BLUE = 0.0722;
    jint *pixelArray = env->GetIntArrayElements(pixelArray_, NULL);
    jsize size = env ->GetArrayLength(pixelArray_);
    jint A, R, G, B, level;
    for (jint i = 0; i < size; i++) {
        A = Color::alpha(pixelArray[i]);
        R = Color::red(pixelArray[i]);
        G = Color::green(pixelArray[i]);
        B = Color::blue(pixelArray[i]);
        level = (jint) (R * GS_RED + G * GS_GREEN + B * GS_BLUE);
        pixelArray[i] = Color::argb(A, level, level, level);
    }
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

    jint index = 0;
    // iteration through pixels
    for (jint i = 0; i < width; i += pixelSize) {
        for (jint j = 0; j < height; j += pixelSize) {
            jint a = 0, r = 0, g = 0, b = 0;
            jint sum = pixelSize * pixelSize;
            for (jint x = i; x < i + pixelSize; x++) {


                for (jint y = j; y < j + pixelSize; y++) {
                    index = x + y * width;
                    if (index < width * height) {
                        a = Color::alpha(pixels[index]);
                        r += Color::red(pixels[index]);
                        g += Color::green(pixels[index]);
                        b += Color::blue(pixels[index]);
                    }
                }
            }
            for (jint x = i; x < i + pixelSize; x++) {
                for (jint y = j; y < j + pixelSize; y++) {
                    index = x + y * width;
                    if (x < width && y < height && index < width * height) {
                        pixels[x + y * width] = Color::argb(a,r/sum, g/sum, b/sum);
                    }

                }
            }
        }
    }

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

    const jint RGB_MASK = 0x00FFFFFF;

    jsize size = env ->GetArrayLength(pixelArray_);
    for (int i = 0; i < size; i++) {
            pixelArray[i] ^= RGB_MASK;
        }
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
    jsize size = env ->GetArrayLength(pixelArray_);
    const jint COLOR_MAX = 255;
    // iteration through pixels
    for (jint i = 0; i < size; i++) {
        int randColor = Color::rgb(rand()%COLOR_MAX,
                                   rand()%COLOR_MAX, rand()%COLOR_MAX);
        // OR
        pixelArray[i] |= randColor;
    }
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
    jsize size = env ->GetArrayLength(pixelArray_);
    jint red = 0;
    jint green = 0;
    jint blue = 0;
    jint switchValue = rand() % 3;
    if (switchValue == 0) {
        red = 1;
    } else if (switchValue == 1) {
        green = 1;
    } else {
        blue = 1;
    }
    jint A, R, G, B;
    for (jint i = 0; i < size; i++) {
        A = Color::alpha(pixelArray[i]);
        R = Color::red(pixelArray[i]) * red;
        G = Color::green(pixelArray[i] * green);
        B = Color::blue(pixelArray[i]) * blue;
        pixelArray[i] = Color::argb(A, R, G, B);
    }
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
    jsize size = env ->GetArrayLength(pixelArray_);
    const jint COLOR_MAX = 255;
    jint  threshHold = 0;
    // iteration through pixels
    // Get original pixels

    jint A, R, G, B;
    for (jint i = 0; i < size; i++) {
        A = Color::alpha(pixelArray[i]);
        R = Color::red(pixelArray[i]) ;
        G = Color::green(pixelArray[i] );
        B = Color::blue(pixelArray[i]) ;
        threshHold = rand()%COLOR_MAX;

        if(R > threshHold && G > threshHold && B > threshHold) {
            pixelArray[i] = Color::argb(A,COLOR_MAX, COLOR_MAX, COLOR_MAX);
        } else {
            pixelArray[i] = Color::argb(A, R, G, B);
        }
    }

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
    const jint GAP = 4;
    jint R, G, B;
    jint A;

    for (jint x = 0; x < imageWidth; x++) {
        for (jint y = 0; y < imageHeight; y += GAP) {
            A = R = G = B = 0;

            for (jint w = 0; w < GAP; w++) {
                jint index = (y + w) * imageWidth + x;
                if (index < imageWidth * imageHeight) {
                    //median value
                    A = Color::alpha(pixelArray[index]);
                    R += Color::red(pixelArray[index]);
                    G += Color::green(pixelArray[index]);
                    B += Color::blue(pixelArray[index]);

                }
            }
            for (jint w = 0; w < GAP; w++) {
                jint index = (y + w) * imageWidth + x;
                if (index < imageWidth * imageHeight) {
                    if (w == 0) {
                        pixelArray[(y + w) * imageWidth + x] = Color::argb(A, R / GAP, 0, 0);
                    }
                    if (w == 1) {
                        pixelArray[(y + w) * imageWidth + x] = Color::argb(A, 0, G / GAP, 0);
                    }
                    if (w == 2) {
                        pixelArray[(y + w) * imageWidth + x] = Color::argb(A, 0, 0, B / GAP);
                    }
//					if (w == 3) {
//						pixels[(y + w) * width + x] =Color.argb(A,255, 255, 255);
//					}
                }
            }
        }
    }

    env->ReleaseIntArrayElements(pixelArray_, pixelArray, 0);
    return pixelArray_;

}