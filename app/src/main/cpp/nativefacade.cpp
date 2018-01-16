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
#include <opencv2/core.hpp>
#include <opencv2/opencv.hpp>
#include <opencv2/imgproc.hpp>
#include <opencv2/features2d.hpp>
#include <vector>
#include <android/log.h>
#define LOG_TAG "MikiNdk"
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)
#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

using namespace std;
using namespace cv;
CascadeClassifier face_cascade;
CascadeClassifier eyes_cascade;
void detect(Mat &mat);

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

//openCV
extern "C"
JNIEXPORT void JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_findFeatures(JNIEnv *env, jclass type,
                                                                       jlong addrGray,
                                                                       jlong addrRgba) {

    Mat& mGr  = *(Mat*)addrGray;
    Mat& mRgb = *(Mat*)addrRgba;
    vector<KeyPoint> v;

    Ptr<FeatureDetector> detector = FastFeatureDetector::create(50);
    detector->detect(mGr, v);
    for( unsigned int i = 0; i < v.size(); i++ )
    {
        const KeyPoint& kp = v[i];
        circle(mRgb, Point(kp.pt.x, kp.pt.y), 10, Scalar(255,0,0,255));
    }

}extern "C"
JNIEXPORT jint JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_detectEdges(JNIEnv *env, jclass type,
                                                                      jlong mAddrInput,
                                                                      jlong mAddrOutput) {

    Mat& mInput  = *(Mat*)mAddrInput;
    Mat& mOutput = *(Mat*)mAddrOutput;

    cvtColor(mInput, mOutput, COLOR_BGR2GRAY);
//    Canny(*img, *dst, 10, 100, 3);
    Canny(mInput, mOutput, 50, 150,3);
    if(mInput.rows == mOutput.rows && mInput.cols == mOutput.cols) return 1;
    return 0;

}extern "C"
JNIEXPORT void JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_cartoonify(JNIEnv *env, jclass type,
                                                                     jlong rgb,
                                                                     jlong gray) {

    const int MEDIAN_BLUR_FILTER_SIZE = 7;
    const int LAPLACIAN_FILTER_SIZE = 5;
    const int EDGES_THRESHOLD = 30;
    int repetitions = 5;
    int kSize = 9;
    double sigmaColor = 9;
    double sigmaSpace = 7;

    cv::Mat& edges = *(cv::Mat *) gray;
    cv::medianBlur(edges, edges, MEDIAN_BLUR_FILTER_SIZE);
    cv::Laplacian(edges, edges, CV_8U, LAPLACIAN_FILTER_SIZE);
    cv::Mat mask; cv::threshold(edges, mask, EDGES_THRESHOLD, 255, CV_THRESH_BINARY_INV);

    cv::Mat& src = *(cv::Mat *) rgb;
    cv::cvtColor(src,src,CV_RGBA2RGB);
    cv::Size size = src.size();
    cv::Size smallSize;
    smallSize.width = size.width/4;
    smallSize.height = size.height/4;
    cv::Mat smallImg = cv::Mat(smallSize, CV_8UC3);
    resize(src, smallImg, smallSize, 0, 0, CV_INTER_LINEAR);

    cv::Mat tmp = cv::Mat(smallSize, CV_8UC3);

    for(int i=0; i<repetitions;i++){
        bilateralFilter(smallImg, tmp, kSize, sigmaColor, sigmaSpace);
        bilateralFilter(tmp, smallImg, kSize, sigmaColor, sigmaSpace);
    }

    cv::Mat bigImg;
    resize(smallImg, bigImg, size, 0, 0, CV_INTER_LINEAR);
    cv::Mat dst; bigImg.copyTo(dst,mask);
    cv::medianBlur(dst, src, MEDIAN_BLUR_FILTER_SIZE-4);

}extern "C"
JNIEXPORT void JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_faceDetection(JNIEnv *env, jclass type,
                                                                        jlong nativeObjAddr) {

    Mat& frame  = *(Mat*)nativeObjAddr;
    detect(frame);

}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_initCascadeClassifiers__Ljava_lang_String_2Ljava_lang_String_2(
        JNIEnv *env, jclass type, jstring absolutePathFace_, jstring absolutePathEyes_) {
    const char *absolutePathFace = env->GetStringUTFChars(absolutePathFace_, 0);
    const char *absolutePathEyes = env->GetStringUTFChars(absolutePathEyes_, 0);
    string faceCascadeFileName(absolutePathFace);
    string eyesCascadeFileName(absolutePathEyes);
    //-- 1. Load the cascades
    if( !face_cascade.load( faceCascadeFileName ) ){ LOGE("Miki initCascadeClassifiers failed"); return ; };
    if( !eyes_cascade.load( eyesCascadeFileName ) ){ LOGE("Miki initCascadeClassifiers failed");  return ; };
    LOGD("Miki initCascadeClassifiers succed");
    env->ReleaseStringUTFChars(absolutePathFace_, absolutePathFace);
    env->ReleaseStringUTFChars(absolutePathEyes_, absolutePathEyes);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_initCascadeClassifiers__Landroid_content_res_AssetManager_2(
        JNIEnv *env, jclass type, jobject mgr) {

    // TODO

}

void detect(Mat &frame){

    std::vector<Rect> faces;
    Mat frame_gray;

    cvtColor( frame, frame_gray, CV_BGR2GRAY );
    equalizeHist( frame_gray, frame_gray );

    //-- Detect faces
    face_cascade.detectMultiScale( frame_gray, faces, 1.1, 2, 0|CV_HAAR_SCALE_IMAGE, Size(30, 30) );

    for( size_t i = 0; i < faces.size(); i++ )
    {
        Point center( faces[i].x + faces[i].width*0.5, faces[i].y + faces[i].height*0.5 );
        ellipse( frame, center, Size( faces[i].width*0.5, faces[i].height*0.5), 0, 0, 360, Scalar( 255, 0, 255 ), 4, 8, 0 );

        Mat faceROI = frame_gray( faces[i] );
        std::vector<Rect> eyes;

        //-- In each face, detect eyes
        eyes_cascade.detectMultiScale( faceROI, eyes, 1.1, 2, 0 |CV_HAAR_SCALE_IMAGE, Size(30, 30) );

        for( size_t j = 0; j < eyes.size(); j++ )
        {
            Point center( faces[i].x + eyes[j].x + eyes[j].width*0.5, faces[i].y + eyes[j].y + eyes[j].height*0.5 );
            int radius = cvRound( (eyes[j].width + eyes[j].height)*0.25 );
            circle( frame, center, radius, Scalar( 255, 0, 0 ), 4, 8, 0 );
        }
    }
}
