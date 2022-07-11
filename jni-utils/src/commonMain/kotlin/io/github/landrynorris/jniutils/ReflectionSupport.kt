package io.github.landrynorris.jniutils

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.invoke
import kotlinx.cinterop.pointed
import platform.android.*

fun CPointer<JNIEnvVar>.fromReflectedMethod(reflectedMethod: jobject?): jmethodID? {
    val method = pointed.pointed?.FromReflectedMethod ?: error("JNI is not Oracle Standard")
    return method.invoke(this, reflectedMethod)
}

fun CPointer<JNIEnvVar>.fromReflectedField(field: jobject): jfieldID? {
    val method = pointed.pointed?.FromReflectedField ?: error("JNI is not Oracle Standard")
    return method.invoke(this, field)
}

fun CPointer<JNIEnvVar>.toReflectedMethod(clazz: jclass, methodId: jmethodID, isStatic: Boolean): jobject? {
    val method = pointed.pointed?.ToReflectedMethod ?: error("JNI is not Oracle Standard")
    return method.invoke(this, clazz, methodId, isStatic.toJBoolean())
}

fun CPointer<JNIEnvVar>.toReflectedField(clazz: jclass, fieldId: jfieldID, isStatic: Boolean): jobject? {
    val method = pointed.pointed?.ToReflectedField ?: error("JNI is not Oracle Standard")
    return method.invoke(this, clazz, fieldId, isStatic.toJBoolean())
}
