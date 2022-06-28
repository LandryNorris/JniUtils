package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.*

fun CPointer<JNIEnvVar>.getStaticFieldId(clazz: jclass, name: String, signature: String): jfieldID? {
    val method = pointed.pointed?.GetStaticFieldID ?: error("JNI is not Oracle standard")
    return name.encodeToByteArray().usePinned { pinnedName ->
        signature.encodeToByteArray().usePinned { pinnedSig ->
            method.invoke(this, clazz, pinnedName.addressOf(0),
                pinnedSig.addressOf(0))
        }
    }
}

fun CPointer<JNIEnvVar>.getStaticObjectField(obj: jobject, fieldId: jfieldID?): jobject? {
    val method = pointed.pointed?.GetStaticObjectField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId)
}

fun CPointer<JNIEnvVar>.getStaticIntField(obj: jobject, fieldId: jfieldID?): Int {
    val method = pointed.pointed?.GetStaticIntField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId)
}

fun CPointer<JNIEnvVar>.getStaticLongField(obj: jobject, fieldId: jfieldID?): Long {
    val method = pointed.pointed?.GetStaticLongField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId)
}

fun CPointer<JNIEnvVar>.getStaticDoubleField(obj: jobject, fieldId: jfieldID?): Double {
    val method = pointed.pointed?.GetStaticDoubleField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId)
}

fun CPointer<JNIEnvVar>.getStaticFloatField(obj: jobject, fieldId: jfieldID?): Float {
    val method = pointed.pointed?.GetStaticFloatField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId)
}

fun CPointer<JNIEnvVar>.getStaticShortField(obj: jobject, fieldId: jfieldID?): Short {
    val method = pointed.pointed?.GetStaticShortField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId)
}

fun CPointer<JNIEnvVar>.getStaticCharField(obj: jobject, fieldId: jfieldID?): Char {
    val method = pointed.pointed?.GetStaticCharField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId).toInt().toChar()
}

fun CPointer<JNIEnvVar>.getStaticByteField(obj: jobject, fieldId: jfieldID?): Byte {
    val method = pointed.pointed?.GetStaticByteField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId)
}

fun CPointer<JNIEnvVar>.getStaticBooleanField(obj: jobject, fieldId: jfieldID?): Boolean {
    val method = pointed.pointed?.GetStaticBooleanField ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, fieldId).toBoolean()
}
