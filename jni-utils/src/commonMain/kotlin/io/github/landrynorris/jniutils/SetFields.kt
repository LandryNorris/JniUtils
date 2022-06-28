package io.github.landrynorris.jniutils

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.invoke
import kotlinx.cinterop.pointed
import platform.android.*

fun CPointer<JNIEnvVar>.setObjectField(obj: jobject, fieldId: jfieldID?, value: jobject?) {
    val method = pointed.pointed?.SetObjectField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value)
}

fun CPointer<JNIEnvVar>.setBooleanField(obj: jobject, fieldId: jfieldID?, value: Boolean) {
    val method = pointed.pointed?.SetBooleanField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, if(value) JNI_TRUE.toUByte() else JNI_FALSE.toUByte())
}

fun CPointer<JNIEnvVar>.setByteField(obj: jobject, fieldId: jfieldID?, value: Byte) {
    val method = pointed.pointed?.SetByteField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value)
}

fun CPointer<JNIEnvVar>.setIntField(obj: jobject, fieldId: jfieldID?, value: Int) {
    val method = pointed.pointed?.SetIntField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value)
}

fun CPointer<JNIEnvVar>.setCharField(obj: jobject, fieldId: jfieldID?, value: Char) {
    val method = pointed.pointed?.SetCharField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value.code.toUShort())
}

fun CPointer<JNIEnvVar>.setShortField(obj: jobject, fieldId: jfieldID?, value: Short) {
    val method = pointed.pointed?.SetShortField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value)
}

fun CPointer<JNIEnvVar>.setLongField(obj: jobject, fieldId: jfieldID?, value: Long) {
    val method = pointed.pointed?.SetLongField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value)
}

fun CPointer<JNIEnvVar>.setFloatField(obj: jobject, fieldId: jfieldID?, value: Float) {
    val method = pointed.pointed?.SetFloatField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value)
}

fun CPointer<JNIEnvVar>.setDoubleField(obj: jobject, fieldId: jfieldID?, value: Double) {
    val method = pointed.pointed?.SetDoubleField ?: error("JNI is not Oracle standard")
    method.invoke(this, obj, fieldId, value)
}
