package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.*

fun CPointer<JNIEnvVar>.newString(chars: CPointer<jcharVar>, length: Int): jstring? {
    val method = pointed.pointed?.NewString ?: error("JNI is not Oracle standard")
    return method.invoke(this, chars, length)
}

fun String.toJString(env: CPointer<JNIEnvVar>): jstring? {
    return memScoped {
        env.newString(wcstr.ptr, length)
    }
}

fun CPointer<JNIEnvVar>.getStringLength(string: jstring): Int {
    val method = pointed.pointed?.GetStringLength ?: error("JNI is not Oracle standard")
    return method.invoke(this, string)
}

fun CPointer<JNIEnvVar>.getStringChars(string: jstring): CPointer<jcharVar>? {
    val method = pointed.pointed?.GetStringChars ?: error("JNI is not Oracle standard")
    return method.invoke(this, string, null)
}

fun CPointer<JNIEnvVar>.getString(string: jstring): String {
    val chars = getStringChars(string)
    return chars?.toKString() ?: error("Unable to create a String from the given jstring")
}

fun CPointer<JNIEnvVar>.releaseStringChars(string: jstring, chars: CPointer<jcharVar>?) {
    val method = pointed.pointed?.ReleaseStringChars ?: error("JNI is not Oracle standard")
    return method.invoke(this, string, chars)
}

fun CPointer<JNIEnvVar>.newString(string: String): jstring? {
    val method = pointed.pointed?.NewStringUTF ?: error("JNI is not Oracle standard")
    return memScoped {
        val bytes = string.encodeToByteArray()
        val buffer = allocArray<ByteVar>(bytes.size) {
            value = bytes[it]
        }
        method.invoke(this@newString, buffer)
    }
}

fun CPointer<JNIEnvVar>.newStringUTF(chars: CPointer<ByteVar>): jstring? {
    val method = pointed.pointed?.NewStringUTF ?: error("JNI is not Oracle standard")
    return method.invoke(this, chars)
}

fun CPointer<JNIEnvVar>.getStringUTFLength(string: jstring): Int {
    val method = pointed.pointed?.GetStringUTFLength ?: error("JNI is not Oracle standard")
    return method.invoke(this, string)
}

fun CPointer<JNIEnvVar>.getStringUTFChars(string: jstring): CPointer<ByteVar>? {
    val method = pointed.pointed?.GetStringUTFChars ?: error("JNI is not Oracle standard")
    return method.invoke(this, string, null)
}

fun CPointer<JNIEnvVar>.releaseStringUTFChars(string: jstring, chars: CPointer<ByteVar>?) {
    val method = pointed.pointed?.ReleaseStringUTFChars ?: error("JNI is not Oracle standard")
    return method.invoke(this, string, chars)
}

fun CPointer<JNIEnvVar>.getStringRegion(string: jstring, start: Int, len: Int, buf: CPointer<jcharVar>) {
    val method = pointed.pointed?.GetStringRegion ?: error("JNI is not Oracle standard")
    return method.invoke(this, string, start, len, buf)
}

fun CPointer<JNIEnvVar>.getStringUTFRegion(string: jstring, start: Int, len: Int, buf: CPointer<ByteVar>) {
    val method = pointed.pointed?.GetStringUTFRegion ?: error("JNI is not Oracle standard")
    return method.invoke(this, string, start, len, buf)
}

fun CPointer<JNIEnvVar>.getStringCritical(string: jstring): CPointer<jcharVar>? {
    val method = pointed.pointed?.GetStringCritical ?: error("JNI is not Oracle standard")
    return method.invoke(this, string, null)
}

fun CPointer<JNIEnvVar>.releaseStringCritical(string: jstring, chars: CPointer<jcharVar>?) {
    val method = pointed.pointed?.ReleaseStringCritical ?: error("JNI is not Oracle standard")
    return method.invoke(this, string, chars)
}
