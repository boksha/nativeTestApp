#include <jni.h>
#include <string>
#include <math.h>

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
}extern "C"
JNIEXPORT jintArray JNICALL
Java_com_example_miodragmilosevic_nativetest_NativeHelper_grayScaleFilter(JNIEnv *env,
                                                                          jobject instance,
                                                                          jintArray pixelArray_) {
    jint *pixelArray = env->GetIntArrayElements(pixelArray_, NULL);

    // TODO

    env->ReleaseIntArrayElements(pixelArray_, pixelArray, 0);
}