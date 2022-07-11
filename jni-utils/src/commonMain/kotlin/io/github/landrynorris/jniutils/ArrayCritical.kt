package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.JNIEnvVar
import platform.android.jarray

/**
 * Gets a pointer to a primitive array, but has restrictions listed below:
 *
 * 1. Each call to [getPrimitiveArrayCritical] MUST be paired with a call to [releasePrimitiveArrayCritical]
 * The region between is called the 'critical region'
 * 2. The critical region MUST NOT be very long
 * 3. There MUST NOT be any other JNI calls inside the critical region
 * 4. Multiple levels of [getPrimitiveArrayCritical] and [releasePrimitiveArrayCritical] MAY be nested,
 * but they must be in the form of nested pairs.
 *
 * This method blocks the garbage collector from running until a corresponding [releasePrimitiveArrayCritical]
 * is called
 */
fun CPointer<JNIEnvVar>.getPrimitiveArrayCritical(array: jarray): CPointer<*>? {
    val method = pointed.pointed?.GetPrimitiveArrayCritical ?: error("JNI is not Oracle standard")
    return method.invoke(this, array, null)
}

/**
 * Gets a pointer to a primitive array, but has restrictions listed below:
 *
 * 1. Each call to [getPrimitiveArrayCritical] MUST be paired with a call to [releasePrimitiveArrayCritical]
 * The region between is called the 'critical region'
 * 2. The critical region MUST NOT be very long
 * 3. There MUST NOT be any other JNI calls inside the critical region
 * 4. Multiple levels of [getPrimitiveArrayCritical] and [releasePrimitiveArrayCritical] MAY be nested,
 * but they must be in the form of nested pairs.
 *
 * If this is the outer level of nested pairs, or there is no nesting involved, the garbage collector will resume.
 */
fun CPointer<JNIEnvVar>.releasePrimitiveArrayCritical(array: jarray, contents: CPointer<*>, mode: Int = 0) {
    val method = pointed.pointed?.ReleasePrimitiveArrayCritical ?: error("JNI is not Oracle standard")
    method.invoke(this, array, contents, mode)
}
