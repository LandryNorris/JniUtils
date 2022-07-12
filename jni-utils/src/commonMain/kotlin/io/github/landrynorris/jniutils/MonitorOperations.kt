package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.JNIEnvVar
import platform.android.JNINativeMethod
import platform.android.jobject

/**
 * Enter the monitor owned by obj
 *
 * Do not mix JNI [monitorExit] with Java synchronized method
 */
fun CPointer<JNIEnvVar>.monitorEnter(obj: jobject) {
    val method = pointed.pointed?.MonitorEnter ?: error("JNI is not Oracle standard")
    method.invoke(this, obj)
}

/**
 * The current thread must be the owner of the monitor associated with obj.
 *
 * Do not mix JNI [monitorExit] with Java synchronized method
 */
fun CPointer<JNIEnvVar>.monitorExit(obj: jobject) {
    val method = pointed.pointed?.MonitorExit ?: error("JNI is not Oracle standard")
    method.invoke(this, obj)
}
