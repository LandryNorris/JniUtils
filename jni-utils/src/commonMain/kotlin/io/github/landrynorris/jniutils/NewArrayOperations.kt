package io.github.landrynorris.jniutils

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.invoke
import kotlinx.cinterop.pointed
import platform.android.*

fun CPointer<JNIEnvVar>.newBooleanArray(size: Int): jbooleanArray? {
    val method = pointed.pointed?.NewBooleanArray ?: error("JNI is not Oracle standard")
    return method.invoke(this, size)
}

fun CPointer<JNIEnvVar>.newByteArray(size: Int): jbyteArray? {
    val method = pointed.pointed?.NewByteArray ?: error("JNI is not Oracle standard")
    return method.invoke(this, size)
}

fun CPointer<JNIEnvVar>.newCharArray(size: Int): jcharArray? {
    val method = pointed.pointed?.NewCharArray ?: error("JNI is not Oracle standard")
    return method.invoke(this, size)
}

fun CPointer<JNIEnvVar>.newShortArray(size: Int): jshortArray? {
    val method = pointed.pointed?.NewShortArray ?: error("JNI is not Oracle standard")
    return method.invoke(this, size)
}

fun CPointer<JNIEnvVar>.newIntArray(size: Int): jintArray? {
    val method = pointed.pointed?.NewIntArray ?: error("JNI is not Oracle standard")
    return method.invoke(this, size)
}

fun CPointer<JNIEnvVar>.newLongArray(size: Int): jlongArray? {
    val method = pointed.pointed?.NewLongArray ?: error("JNI is not Oracle standard")
    return method.invoke(this, size)
}

fun CPointer<JNIEnvVar>.newFloatArray(size: Int): jfloatArray? {
    val method = pointed.pointed?.NewFloatArray ?: error("JNI is not Oracle standard")
    return method.invoke(this, size)
}

fun CPointer<JNIEnvVar>.newDoubleArray(size: Int): jdoubleArray? {
    val method = pointed.pointed?.NewDoubleArray ?: error("JNI is not Oracle standard")
    return method.invoke(this, size)
}
