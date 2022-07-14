package io.github.landrynorris.jniutils

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.get
import kotlinx.cinterop.invoke
import kotlinx.cinterop.pointed
import platform.android.*

fun CPointer<JNIEnvVar>.getBooleanArrayElements(array: jbooleanArray): BooleanArray {
    val method = pointed.pointed?.GetBooleanArrayElements ?: error("JNI is not Oracle standard")
    val size = getArrayLength(array)
    val data = method.invoke(this, array, null)

    return (0 until size).map {
        data?.get(it)?.toBoolean() ?: false
    }.toBooleanArray().also {
        if(data != null) releaseBooleanArrayElements(array, data)
    }
}

fun CPointer<JNIEnvVar>.getByteArrayElements(array: jbyteArray): ByteArray {
    val method = pointed.pointed?.GetByteArrayElements ?: error("JNI is not Oracle standard")
    val size = getArrayLength(array)
    val data = method.invoke(this, array, null)

    return (0 until size).map {
        data?.get(it)?.toByte() ?: 0
    }.toByteArray().also {
        if(data != null) releaseByteArrayElements(array, data)
    }
}

fun CPointer<JNIEnvVar>.getCharArrayElements(array: jcharArray): CharArray {
    val method = pointed.pointed?.GetCharArrayElements ?: error("JNI is not Oracle standard")
    val size = getArrayLength(array)
    val data = method.invoke(this, array, null)

    return (0 until size).map {
        data?.get(it)?.toInt()?.toChar() ?: 0.toChar()
    }.toCharArray().also {
        if(data != null) releaseCharArrayElements(array, data)
    }
}

fun CPointer<JNIEnvVar>.getShortArrayElements(array: jshortArray): ShortArray {
    val method = pointed.pointed?.GetShortArrayElements ?: error("JNI is not Oracle standard")
    val size = getArrayLength(array)
    val data = method.invoke(this, array, null)

    return (0 until size).map {
        data?.get(it)?.toShort() ?: 0
    }.toShortArray().also {
        if(data != null) releaseShortArrayElements(array, data)
    }
}

fun CPointer<JNIEnvVar>.getIntArrayElements(array: jintArray): IntArray {
    val method = pointed.pointed?.GetIntArrayElements ?: error("JNI is not Oracle standard")
    val size = getArrayLength(array)
    val data = method.invoke(this, array, null)

    return (0 until size).map {
        data?.get(it)?.toInt() ?: 0
    }.toIntArray().also {
        if(data != null) releaseIntArrayElements(array, data)
    }
}

fun CPointer<JNIEnvVar>.getLongArrayElements(array: jlongArray): LongArray {
    val method = pointed.pointed?.GetLongArrayElements ?: error("JNI is not Oracle standard")
    val size = getArrayLength(array)
    val data = method.invoke(this, array, null)

    return (0 until size).map {
        data?.get(it)?.toLong() ?: 0
    }.toLongArray().also {
        if(data != null) releaseLongArrayElements(array, data)
    }
}

fun CPointer<JNIEnvVar>.getFloatArrayElements(array: jfloatArray): FloatArray {
    val method = pointed.pointed?.GetFloatArrayElements ?: error("JNI is not Oracle standard")
    val size = getArrayLength(array)
    val data = method.invoke(this, array, null)

    return (0 until size).map {
        data?.get(it)?.toFloat() ?: 0f
    }.toFloatArray().also {
        if(data != null) releaseFloatArrayElements(array, data)
    }
}

fun CPointer<JNIEnvVar>.getDoubleArrayElements(array: jdoubleArray): DoubleArray {
    val method = pointed.pointed?.GetDoubleArrayElements ?: error("JNI is not Oracle standard")
    val size = getArrayLength(array)
    val data = method.invoke(this, array, null)

    return (0 until size).map {
        data?.get(it)?.toDouble() ?: 0.0
    }.toDoubleArray().also {
        if(data != null) releaseDoubleArrayElements(array, data)
    }
}
