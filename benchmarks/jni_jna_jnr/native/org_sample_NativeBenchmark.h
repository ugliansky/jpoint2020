/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_sample_NativeBenchmark */

#ifndef _Included_org_sample_NativeBenchmark
#define _Included_org_sample_NativeBenchmark
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_sample_NativeBenchmark
 * Method:    goNative
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_sample_NativeBenchmark_goNative
  (JNIEnv *, jclass);

JNIEXPORT void JNICALL Java_org_sample_NativeBenchmark_goNativeInstance
  (JNIEnv *, jobject);

/*
 * Class:     org_sample_NativeBenchmark
 * Method:    goNativeAndBack
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_sample_NativeBenchmark_goNativeAndBack
  (JNIEnv *, jclass);

/*
 * Class:     org_sample_NativeBenchmark
 * Method:    goNativeWithInt
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_sample_NativeBenchmark_goNativeWithInt
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_sample_NativeBenchmark
 * Method:    goNativeWithObject
 * Signature: (Ljava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_org_sample_NativeBenchmark_goNativeWithObject
  (JNIEnv *, jclass, jobject);

#ifdef __cplusplus
}
#endif
#endif
