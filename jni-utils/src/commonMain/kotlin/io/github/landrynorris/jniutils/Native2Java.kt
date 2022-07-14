package io.github.landrynorris.jniutils

import kotlinx.cinterop.CPointer
import platform.android.*

fun BooleanArray.toJava(env: CPointer<JNIEnvVar>): jbooleanArray {
    val result = env.newBooleanArray(size)!!
    env.setBooleanArrayRegion(result, 0, size, this)
    return result
}

fun ByteArray.toJava(env: CPointer<JNIEnvVar>): jbyteArray {
    val result = env.newByteArray(size)!!
    env.setByteArrayRegion(result, 0, size, this)
    return result
}

fun ShortArray.toJava(env: CPointer<JNIEnvVar>): jshortArray {
    val result = env.newShortArray(size)!!
    env.setShortArrayRegion(result, 0, size, this)
    return result
}

fun CharArray.toJava(env: CPointer<JNIEnvVar>): jcharArray {
    val result = env.newCharArray(size)!!
    env.setCharArrayRegion(result, 0, size, this)
    return result
}

fun IntArray.toJava(env: CPointer<JNIEnvVar>): jintArray {
    val result = env.newIntArray(size)!!
    env.setIntArrayRegion(result, 0, size, this)
    return result
}

fun LongArray.toJava(env: CPointer<JNIEnvVar>): jlongArray {
    val result = env.newLongArray(size)!!
    env.setLongArrayRegion(result, 0, size, this)
    return result
}

fun FloatArray.toJava(env: CPointer<JNIEnvVar>): jfloatArray {
    val result = env.newFloatArray(size)!!
    env.setFloatArrayRegion(result, 0, size, this)
    return result
}

fun DoubleArray.toJava(env: CPointer<JNIEnvVar>): jdoubleArray {
    val result = env.newDoubleArray(size)!!
    env.setDoubleArrayRegion(result, 0, size, this)
    return result
}
