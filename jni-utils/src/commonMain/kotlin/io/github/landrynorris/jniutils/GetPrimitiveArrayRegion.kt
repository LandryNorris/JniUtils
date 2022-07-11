package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.*

fun CPointer<JNIEnvVar>.getBooleanArrayRegion(array: jbooleanArray, start: Int,
                                              length: Int, buffer: CArrayPointer<jbooleanVar>) {
    val method = pointed.pointed?.GetBooleanArrayRegion ?: error("JNI is not Oracle standard")
    method.invoke(this@getBooleanArrayRegion, array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.getByteArrayRegion(array: jbyteArray, start: Int,
                                              length: Int, buffer: CArrayPointer<jbyteVar>) {
    val method = pointed.pointed?.GetByteArrayRegion ?: error("JNI is not Oracle standard")
    method.invoke(this@getByteArrayRegion, array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.getShortArrayRegion(array: jshortArray, start: Int,
                                              length: Int, buffer: CArrayPointer<jshortVar>) {
    val method = pointed.pointed?.GetShortArrayRegion ?: error("JNI is not Oracle standard")
    method.invoke(this@getShortArrayRegion, array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.getCharArrayRegion(array: jcharArray, start: Int,
                                              length: Int, buffer: CArrayPointer<jcharVar>) {
    val method = pointed.pointed?.GetCharArrayRegion ?: error("JNI is not Oracle standard")
    method.invoke(this@getCharArrayRegion, array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.getIntArrayRegion(array: jintArray, start: Int,
                                              length: Int, buffer: CArrayPointer<jintVar>) {
    val method = pointed.pointed?.GetIntArrayRegion ?: error("JNI is not Oracle standard")
    method.invoke(this@getIntArrayRegion, array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.getLongArrayRegion(array: jlongArray, start: Int,
                                              length: Int, buffer: CArrayPointer<jlongVar>) {
    val method = pointed.pointed?.GetLongArrayRegion ?: error("JNI is not Oracle standard")
    method.invoke(this@getLongArrayRegion, array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.getFloatArrayRegion(array: jfloatArray, start: Int,
                                              length: Int, buffer: CArrayPointer<jfloatVar>) {
    val method = pointed.pointed?.GetFloatArrayRegion ?: error("JNI is not Oracle standard")
    method.invoke(this@getFloatArrayRegion, array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.getDoubleArrayRegion(array: jdoubleArray, start: Int,
                                              length: Int, buffer: CArrayPointer<jdoubleVar>) {
    val method = pointed.pointed?.GetDoubleArrayRegion ?: error("JNI is not Oracle standard")
    method.invoke(this@getDoubleArrayRegion, array, start, length, buffer)
}

/*
Kotlin integration
************************************************************************************************
 */

fun CPointer<JNIEnvVar>.getBooleanArrayRegion(array: jbooleanArray, start: Int,
                                              length: Int): BooleanArray = memScoped {
    val buffer = allocArray<jbooleanVar>(length)
    getBooleanArrayRegion(array, start, length, buffer)

    return (0 until length).map {
        buffer[it].toBoolean()
    }.toBooleanArray()
}

fun CPointer<JNIEnvVar>.getByteArrayRegion(array: jbyteArray, start: Int,
                                              length: Int): ByteArray = memScoped {
    val buffer = allocArray<jbyteVar>(length)
    getByteArrayRegion(array, start, length, buffer)

    return (0 until length).map {
        buffer[it]
    }.toByteArray()
}

fun CPointer<JNIEnvVar>.getShortArrayRegion(array: jshortArray, start: Int,
                                           length: Int): ShortArray = memScoped {
    val buffer = allocArray<jshortVar>(length)
    getShortArrayRegion(array, start, length, buffer)

    return (0 until length).map {
        buffer[it]
    }.toShortArray()
}

fun CPointer<JNIEnvVar>.getCharArrayRegion(array: jcharArray, start: Int,
                                           length: Int): CharArray = memScoped {
    val buffer = allocArray<jcharVar>(length)
    getCharArrayRegion(array, start, length, buffer)

    return (0 until length).map {
        buffer[it].toInt().toChar()
    }.toCharArray()
}

fun CPointer<JNIEnvVar>.getIntArrayRegion(array: jintArray, start: Int,
                                           length: Int): IntArray = memScoped {
    val buffer = allocArray<jintVar>(length)
    getIntArrayRegion(array, start, length, buffer)

    return (0 until length).map {
        buffer[it]
    }.toIntArray()
}

fun CPointer<JNIEnvVar>.getLongArrayRegion(array: jlongArray, start: Int,
                                           length: Int): LongArray = memScoped {
    val buffer = allocArray<jlongVar>(length)
    getLongArrayRegion(array, start, length, buffer)

    return (0 until length).map {
        buffer[it]
    }.toLongArray()
}

fun CPointer<JNIEnvVar>.getFloatArrayRegion(array: jfloatArray, start: Int,
                                           length: Int): FloatArray = memScoped {
    val buffer = allocArray<jfloatVar>(length)
    getFloatArrayRegion(array, start, length, buffer)

    return (0 until length).map {
        buffer[it]
    }.toFloatArray()
}

fun CPointer<JNIEnvVar>.getDoubleArrayRegion(array: jdoubleArray, start: Int,
                                           length: Int): DoubleArray = memScoped {
    val buffer = allocArray<jdoubleVar>(length)
    getDoubleArrayRegion(array, start, length, buffer)

    return (0 until length).map {
        buffer[it]
    }.toDoubleArray()
}
