package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.JNIEnvVar
import platform.android.JNINativeMethod
import platform.android.jobject

fun CPointer<JNIEnvVar>.monitorEnter(obj: jobject) {
    val method = pointed.pointed?.MonitorEnter ?: error("JNI is not Oracle standard")
    method.invoke(this, obj)
}

fun CPointer<JNIEnvVar>.monitorExit(obj: jobject) {
    val method = pointed.pointed?.MonitorExit ?: error("JNI is not Oracle standard")
    method.invoke(this, obj)
}
