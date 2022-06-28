package io.github.landrynorris.jniutils

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.invoke
import kotlinx.cinterop.pointed
import platform.android.JNIEnvVar
import platform.android.jobject
import platform.android.jweak

fun CPointer<JNIEnvVar>.newLocalRef(ref: jobject?): jobject? {
    val method = pointed.pointed?.NewLocalRef ?: error("JNI is not Oracle standard")
    return method.invoke(this, ref)
}

fun CPointer<JNIEnvVar>.newWeakGlobalRef(obj: jobject?): jobject? {
    val method = pointed.pointed?.NewWeakGlobalRef ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj)
}

fun CPointer<JNIEnvVar>.deleteWeakGlobalRef(result: jweak?) {
    val method = pointed.pointed?.DeleteWeakGlobalRef ?: error("JNI is not Oracle standard")
    method.invoke(this, result)
}

fun CPointer<JNIEnvVar>.newGlobalRef(obj: jobject): jobject? {
    val method = pointed.pointed?.NewGlobalRef ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj)
}

fun CPointer<JNIEnvVar>.deleteGlobalRef(globalRef: jobject) {
    val method = pointed.pointed?.DeleteGlobalRef ?: error("JNI is not Oracle standard")
    method.invoke(this, globalRef)
}

fun CPointer<JNIEnvVar>.deleteLocalRef(localRef: jobject) {
    val method = pointed.pointed?.DeleteLocalRef ?: error("JNI is not Oracle standard")
    method.invoke(this, localRef)
}
