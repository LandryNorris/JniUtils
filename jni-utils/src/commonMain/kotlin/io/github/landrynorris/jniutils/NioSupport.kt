package io.github.landrynorris.jniutils

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.invoke
import kotlinx.cinterop.pointed
import platform.android.JNIEnvVar
import platform.android.jobject

fun CPointer<JNIEnvVar>.newDirectByteBuffer(address: COpaquePointer, capacity: Long): jobject? {
    val method = pointed.pointed?.NewDirectByteBuffer ?: error("JNI is not Oracle Standard")
    return method.invoke(this, address, capacity)
}

fun CPointer<JNIEnvVar>.getDirectByteBufferAddress(buffer: jobject): COpaquePointer? {
    val method = pointed.pointed?.GetDirectBufferAddress ?: error("JNI is not Oracle Standard")
    return method.invoke(this, buffer)
}

fun CPointer<JNIEnvVar>.getDirectByteBufferCapacity(buffer: jobject): Long {
    val method = pointed.pointed?.GetDirectBufferCapacity ?: error("JNI is not Oracle Standard")
    return method.invoke(this, buffer)
}
