package io.github.landrynorris.jniutils

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.invoke
import kotlinx.cinterop.pointed
import platform.android.JNIEnvVar
import platform.android.jfieldID
import platform.android.jobject

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
