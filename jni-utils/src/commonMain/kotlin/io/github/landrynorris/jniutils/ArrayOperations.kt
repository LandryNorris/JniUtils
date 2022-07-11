package io.github.landrynorris.jniutils

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.invoke
import kotlinx.cinterop.pointed
import platform.android.*

/**
 * @param array the array to check
 * @return the number of elements in [array]
 */
fun CPointer<JNIEnvVar>.getArrayLength(array: jarray): Int {
    val method = pointed.pointed?.GetArrayLength ?: error("JNI is not Oracle standard")
    return method.invoke(this, array)
}

/**
 * @param length the length of the new array
 * @param elementClass the Java class of elements
 * @param initial the initial value for each index
 *
 * @return a new Java array with the given length and type
 */
fun CPointer<JNIEnvVar>.newObjectArray(length: Int, elementClass: jclass, initial: jobject): jobjectArray? {
    val method = pointed.pointed?.NewObjectArray ?: error("JNI is not Oracle standard")
    return method.invoke(this, length, elementClass, initial)
}

/**
 * @param array the array to get an element from
 * @param index the index of [array]
 *
 * @return the element at [index] of [array]
 */
fun CPointer<JNIEnvVar>.getObjectArrayElement(array: jobjectArray, index: Int): jobject? {
    val method = pointed.pointed?.GetObjectArrayElement ?: error("JNI is not Oracle standard")
    return method.invoke(this, array, index)
}

/**
 * @param array the array to set the element in
 * @param index the index of [array]
 * @param value the value to set at the given array index
 */
fun CPointer<JNIEnvVar>.setObjectArrayElement(array: jobjectArray, index: Int, value: jobject?) {
    val method = pointed.pointed?.SetObjectArrayElement ?: error("JNI is not Oracle standard")
    return method.invoke(this, array, index, value)
}
