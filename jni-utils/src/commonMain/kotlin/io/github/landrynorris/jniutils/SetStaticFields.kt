package io.github.landrynorris.jniutils

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.invoke
import kotlinx.cinterop.pointed
import platform.android.*

fun CPointer<JNIEnvVar>.setStaticObjectField(obj: jobject, fieldId: jfieldID?, value: jobject) {
    val method = pointed.pointed?.SetStaticObjectField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value)
}

fun CPointer<JNIEnvVar>.setStaticIntField(obj: jobject, fieldId: jfieldID?, value: Int) {
    val method = pointed.pointed?.SetStaticIntField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value)
}

fun CPointer<JNIEnvVar>.setStaticLongField(obj: jobject, fieldId: jfieldID?, value: Long) {
    val method = pointed.pointed?.SetStaticLongField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value)
}

fun CPointer<JNIEnvVar>.setStaticDoubleField(obj: jobject, fieldId: jfieldID?, value: Double) {
    val method = pointed.pointed?.SetStaticDoubleField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value)
}

fun CPointer<JNIEnvVar>.setStaticFloatField(obj: jobject, fieldId: jfieldID?, value: Float) {
    val method = pointed.pointed?.SetStaticFloatField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value)
}

fun CPointer<JNIEnvVar>.setStaticShortField(obj: jobject, fieldId: jfieldID?, value: Short) {
    val method = pointed.pointed?.SetStaticShortField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value)
}

fun CPointer<JNIEnvVar>.setStaticByteField(obj: jobject, fieldId: jfieldID?, value: Byte) {
    val method = pointed.pointed?.SetStaticByteField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value)
}

fun CPointer<JNIEnvVar>.setStaticCharField(obj: jobject, fieldId: jfieldID?, value: Char) {
    val method = pointed.pointed?.SetStaticCharField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value.code.toUShort())
}

fun CPointer<JNIEnvVar>.setStaticBooleanField(obj: jobject, fieldId: jfieldID?, value: Boolean) {
    val method = pointed.pointed?.SetStaticBooleanField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value.toJBoolean())
}
