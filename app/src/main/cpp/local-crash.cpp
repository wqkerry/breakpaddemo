#include<stdio.h>
#include<jni.h>

void Crash(){
   volatile int *a=(int *)(NULL);
     *a=1;
}

extern "C"

JNIEXPORT void JNICALL
Java_com_swangq_com_suanfa_BreakpadActivity_crash(JNIEnv *, jobject jobject){
    Crash();
}

