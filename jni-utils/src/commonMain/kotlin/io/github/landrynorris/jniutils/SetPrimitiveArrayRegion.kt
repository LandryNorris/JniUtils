package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.*

fun CPointer<JNIEnvVar>.setBooleanArrayRegion(array: jbooleanArray, start: Int,
                                              length: Int, buffer: CArrayPointer<jbooleanVar>) {
    val method = pointed.pointed?.SetBooleanArrayRegion ?: error("JNI is not Oracle standard")
    method.invoke(this, array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.setByteArrayRegion(array: jbyteArray, start: Int,
                                           length: Int, buffer: CArrayPointer<jbyteVar>) {
    val method = pointed.pointed?.SetByteArrayRegion ?: error("JNI is not Oracle standard")
    method.invoke(this, array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.setShortArrayRegion(array: jshortArray, start: Int,
                                           length: Int, buffer: CArrayPointer<jshortVar>) {
    val method = pointed.pointed?.SetShortArrayRegion ?: error("JNI is not Oracle standard")
    method.invoke(this, array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.setCharArrayRegion(array: jcharArray, start: Int,
                                           length: Int, buffer: CArrayPointer<jcharVar>) {
    val method = pointed.pointed?.SetCharArrayRegion ?: error("JNI is not Oracle standard")
    method.invoke(this, array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.setIntArrayRegion(array: jintArray, start: Int,
                                           length: Int, buffer: CArrayPointer<jintVar>) {
    val method = pointed.pointed?.SetIntArrayRegion ?: error("JNI is not Oracle standard")
    method.invoke(this, array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.setLongArrayRegion(array: jlongArray, start: Int,
                                           length: Int, buffer: CArrayPointer<jlongVar>) {
    val method = pointed.pointed?.SetLongArrayRegion ?: error("JNI is not Oracle standard")
    method.invoke(this, array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.setFloatArrayRegion(array: jfloatArray, start: Int,
                                           length: Int, buffer: CArrayPointer<jfloatVar>) {
    val method = pointed.pointed?.SetFloatArrayRegion ?: error("JNI is not Oracle standard")
    method.invoke(this, array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.setDoubleArrayRegion(array: jdoubleArray, start: Int,
                                           length: Int, buffer: CArrayPointer<jdoubleVar>) {
    val method = pointed.pointed?.SetDoubleArrayRegion ?: error("JNI is not Oracle standard")
    method.invoke(this, array, start, length, buffer)
}

/*
Kotlin integration
************************************************************************************************
 */

fun CPointer<JNIEnvVar>.setBooleanArrayRegion(array: jbooleanArray, start: Int,
                                              length: Int, values: BooleanArray) = memScoped {
    val buffer = allocArray<jbooleanVar>(length)
    values.forEachIndexed { index, value -> buffer[index] = value.toJBoolean() }
    setBooleanArrayRegion(array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.setByteArrayRegion(array: jbyteArray, start: Int,
                                           length: Int, values: ByteArray) = memScoped {
    val buffer = allocArray<jbyteVar>(length)
    values.forEachIndexed { index, value -> buffer[index] = value }
    setByteArrayRegion(array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.setShortArrayRegion(array: jshortArray, start: Int,
                                              length: Int, values: ShortArray) = memScoped {
    val buffer = allocArray<jshortVar>(length)
    values.forEachIndexed { index, value -> buffer[index] = value }
    setShortArrayRegion(array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.setCharArrayRegion(array: jcharArray, start: Int,
                                            length: Int, values: CharArray) = memScoped {
    val buffer = allocArray<jcharVar>(length)
    values.forEachIndexed { index, value -> buffer[index] = value.code.toUShort() }
    setCharArrayRegion(array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.setIntArrayRegion(array: jintArray, start: Int,
                                           length: Int, values: IntArray) = memScoped {
    val buffer = allocArray<jintVar>(length)
    values.forEachIndexed { index, value -> buffer[index] = value }
    setIntArrayRegion(array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.setLongArrayRegion(array: jlongArray, start: Int,
                                           length: Int, values: LongArray) = memScoped {
    val buffer = allocArray<jlongVar>(length)
    values.forEachIndexed { index, value -> buffer[index] = value }
    setLongArrayRegion(array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.setFloatArrayRegion(array: jfloatArray, start: Int,
                                           length: Int, values: FloatArray) = memScoped {
    val buffer = allocArray<jfloatVar>(length)
    values.forEachIndexed { index, value -> buffer[index] = value }
    setFloatArrayRegion(array, start, length, buffer)
}

fun CPointer<JNIEnvVar>.setDoubleArrayRegion(array: jdoubleArray, start: Int,
                                           length: Int, values: DoubleArray) = memScoped {
    val buffer = allocArray<jdoubleVar>(length)
    values.forEachIndexed { index, value -> buffer[index] = value }
    setDoubleArrayRegion(array, start, length, buffer)
}
