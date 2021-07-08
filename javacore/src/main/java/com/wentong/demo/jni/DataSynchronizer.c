#include <jni.h>
#include <stdio.h>
#include "DataSynchronizer.h"

JNIEXPORT jstring JNICALL Java_DataSynchronizer_syncData(JNIEnv *env, jobject obj, jstring str) {
   // Step 1: Convert the JNI String (jstring) into C-String (char*)
   const char *inCStr = (*env)->GetStringUTFChars(env, str, NULL);
   if (NULL == inCStr) {
        return NULL;
    }

   // Step 2: Perform its intended operations
   printf("In C, the received string is: %s\n", inCStr);
   (*env)->ReleaseStringUTFChars(env, str, inCStr);  // release resources

   // Prompt user for a C-string
   char outCStr[128];
   printf("Enter a String: ");
   scanf("%s", outCStr);

   // Step 3: Convert the C-string (char*) into JNI String (jstring) and return
   return (*env)->NewStringUTF(env, outCStr);
}
