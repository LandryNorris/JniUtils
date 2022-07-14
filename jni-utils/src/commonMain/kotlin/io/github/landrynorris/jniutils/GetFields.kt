package io.github.landrynorris.jniutils

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.invoke
import kotlinx.cinterop.pointed
import platform.android.*

fun CPointer<JNIEnvVar>.getObjectField(obj: jobject, fieldId: jfieldID?): jobject? {
    val method = pointed.pointed?.GetObjectField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId)
}

fun CPointer<JNIEnvVar>.getBooleanField(obj: jobject, fieldId: jfieldID?): Boolean {
    val method = pointed.pointed?.GetBooleanField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId).toBoolean()
}

fun CPointer<JNIEnvVar>.getByteField(obj: jobject, fieldId: jfieldID?): Byte {
    val method = pointed.pointed?.GetByteField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId)
}

fun CPointer<JNIEnvVar>.getIntField(obj: jobject, fieldId: jfieldID?): Int {
    val method = pointed.pointed?.GetIntField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId)
}

fun CPointer<JNIEnvVar>.getCharField(obj: jobject, fieldId: jfieldID?): Char {
    val method = pointed.pointed?.GetCharField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId).toInt().toChar()
}

fun CPointer<JNIEnvVar>.getShortField(obj: jobject, fieldId: jfieldID?): Short {
    val method = pointed.pointed?.GetShortField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId)
}

fun CPointer<JNIEnvVar>.getLongField(obj: jobject, fieldId: jfieldID?): Long {
    val method = pointed.pointed?.GetLongField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId)
}

fun CPointer<JNIEnvVar>.getFloatField(obj: jobject, fieldId: jfieldID?): Float {
    val method = pointed.pointed?.GetFloatField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId)
}

fun CPointer<JNIEnvVar>.getDoubleField(obj: jobject, fieldId: jfieldID?): Double {
    val method = pointed.pointed?.GetDoubleField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId)
}

fun CPointer<JNIEnvVar>.getBooleanField(obj: jobject, name: String, clazz: jclass): Boolean {
    return getBooleanField(obj, getFieldId(clazz, name, Boolean.toString()))
}

fun CPointer<JNIEnvVar>.getByteField(obj: jobject, name: String, clazz: jclass): Byte {
    return getByteField(obj, getFieldId(clazz, name, Byte.toString()))
}

fun CPointer<JNIEnvVar>.getShortField(obj: jobject, name: String, clazz: jclass): Short {
    return getShortField(obj, getFieldId(clazz, name, Short.toString()))
}

fun CPointer<JNIEnvVar>.getCharField(obj: jobject, name: String, clazz: jclass): Char {
    return getCharField(obj, getFieldId(clazz, name, Char.toString()))
}

fun CPointer<JNIEnvVar>.getIntField(obj: jobject, name: String, clazz: jclass): Int {
    return getIntField(obj, getFieldId(clazz, name, Int.toString()))
}

fun CPointer<JNIEnvVar>.getLongField(obj: jobject, name: String, clazz: jclass): Long {
    return getLongField(obj, getFieldId(clazz, name, Long.toString()))
}

fun CPointer<JNIEnvVar>.getFloatField(obj: jobject, name: String, clazz: jclass): Float {
    return getFloatField(obj, getFieldId(clazz, name, Float.toString()))
}

fun CPointer<JNIEnvVar>.getDoubleField(obj: jobject, name: String, clazz: jclass): Double {
    return getDoubleField(obj, getFieldId(clazz, name, Double.toString()))
}

fun CPointer<JNIEnvVar>.getObjectField(obj: jobject, name: String, clazz: jclass, signature: String): jobject? {
    return getObjectField(obj, getFieldId(clazz, name, signature))
}

fun CPointer<JNIEnvVar>.getStringField(obj: jobject, name: String, clazz: jclass): String {
    val field = getObjectField(obj, name, clazz, String.toString()) as jstring
    return getString(field)
}
