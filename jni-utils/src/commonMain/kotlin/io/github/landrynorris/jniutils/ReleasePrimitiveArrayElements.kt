package io.github.landrynorris.jniutils

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.invoke
import kotlinx.cinterop.pointed
import platform.android.*

fun CPointer<JNIEnvVar>.releaseBooleanArrayElements(array: jbooleanArray, data: CPointer<jbooleanVar>,
                                                    mode: Int = 0) {
    val method = pointed.pointed?.ReleaseBooleanArrayElements ?: error("JNI is not Oracle standard")
    method.invoke(this, array, data, mode)
}

fun CPointer<JNIEnvVar>.releaseByteArrayElements(array: jbyteArray, data: CPointer<jbyteVar>,
                                                    mode: Int = 0) {
    val method = pointed.pointed?.ReleaseByteArrayElements ?: error("JNI is not Oracle standard")
    method.invoke(this, array, data, mode)
}

fun CPointer<JNIEnvVar>.releaseShortArrayElements(array: jshortArray, data: CPointer<jshortVar>,
                                                    mode: Int = 0) {
    val method = pointed.pointed?.ReleaseShortArrayElements ?: error("JNI is not Oracle standard")
    method.invoke(this, array, data, mode)
}

fun CPointer<JNIEnvVar>.releaseCharArrayElements(array: jcharArray, data: CPointer<jcharVar>,
                                                    mode: Int = 0) {
    val method = pointed.pointed?.ReleaseCharArrayElements ?: error("JNI is not Oracle standard")
    method.invoke(this, array, data, mode)
}

fun CPointer<JNIEnvVar>.releaseIntArrayElements(array: jintArray, data: CPointer<jintVar>,
                                                    mode: Int = 0) {
    val method = pointed.pointed?.ReleaseIntArrayElements ?: error("JNI is not Oracle standard")
    method.invoke(this, array, data, mode)
}

fun CPointer<JNIEnvVar>.releaseLongArrayElements(array: jlongArray, data: CPointer<jlongVar>,
                                                    mode: Int = 0) {
    val method = pointed.pointed?.ReleaseLongArrayElements ?: error("JNI is not Oracle standard")
    method.invoke(this, array, data, mode)
}

fun CPointer<JNIEnvVar>.releaseFloatArrayElements(array: jfloatArray, data: CPointer<jfloatVar>,
                                                    mode: Int = 0) {
    val method = pointed.pointed?.ReleaseFloatArrayElements ?: error("JNI is not Oracle standard")
    method.invoke(this, array, data, mode)
}

fun CPointer<JNIEnvVar>.releaseDoubleArrayElements(array: jdoubleArray, data: CPointer<jdoubleVar>,
                                                    mode: Int = 0) {
    val method = pointed.pointed?.ReleaseDoubleArrayElements ?: error("JNI is not Oracle standard")
    method.invoke(this, array, data, mode)
}
