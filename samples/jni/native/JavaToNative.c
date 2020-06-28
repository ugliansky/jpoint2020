#include <JavaToNative.h>

/*
 * Class:     JavaToNative
 * Method:    goThere
 * Signature: (LCallback;Ljava/lang/Object)V
 */
JNIEXPORT void JNICALL Java_JavaToNative_goThere(JNIEnv * env, jclass klass, jobject andBackAgain) {
    printf("Ok, we are in Mordor now!\n");

    jclass cls = (*env)->GetObjectClass(env, andBackAgain);
    jmethodID method = (*env)->GetMethodID(env, cls, "call", "()V");
    (*env)->CallVoidMethod(env, andBackAgain, method);
}

JNIEXPORT void JNICALL Java_JavaToNative_goNative(JNIEnv *env, jclass klass, jobject obj) {
}

JNIEXPORT void JNICALL Java_AllocationTest_objectsAllocationTest
  (JNIEnv *env, jclass klass) {

    int ready = 0;
    int id = 0;
    jclass cls = (*env)->FindClass(env, "BornInNative");
    jmethodID init = (*env)->GetMethodID(env, cls, "<init>", "(I)V");
    jmethodID check = (*env)->GetMethodID(env, cls, "areYouReady", "()Z");
    while (!ready) {
        jobject obj = (*env)->NewObject(env, cls, init, id++);
        if (!obj) {
            printf("allocation attempt %d failed\n", id);
        }
        ready = (*env)->CallBooleanMethod(env, obj, check) == JNI_TRUE;
        (*env)->DeleteLocalRef(env, obj);
    }
    printf("finally ready after %d objects created!\n", id);
}