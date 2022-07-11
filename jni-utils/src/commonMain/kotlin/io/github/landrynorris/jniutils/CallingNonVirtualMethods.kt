package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.*

/**
 * Invoke a non-virtual method on a [jobject] based on the given [jclass]
 *
 * @param jobj the object to invoke the method on
 * @param jclazz the class to use to invoke the method
 * @param methodId the id of the method to invoke
 *
 */
fun CPointer<JNIEnvVar>.callNonVirtualVoidMethod(jobj: jobject, jclazz: jclass,
                                       methodId: jmethodID,
                                       vararg args: CValue<jvalue>) {
    val method = pointed.pointed?.CallNonvirtualVoidMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callNonVirtualVoidMethod, jobj, jclazz, methodId, ptr)
    }
}

/**
 * Invoke a non-virtual method on a [jobject] based on the given [jclass]
 *
 * @param jobj the object to invoke the method on
 * @param jclazz the class to use to invoke the method
 * @param methodId the id of the method to invoke
 *
 * @return the [jobject] returned by the Java method
 */
fun CPointer<JNIEnvVar>.callNonVirtualObjectMethod(jobj: jobject, jclazz: jclass,
                                                 methodId: jmethodID,
                                                 vararg args: CValue<jvalue>): jobject? {
    val method = pointed.pointed?.CallNonvirtualObjectMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callNonVirtualObjectMethod, jobj, jclazz, methodId, ptr)
    }
}

/**
 * Invoke a non-virtual method on a [jobject] based on the given [jclass]
 *
 * @param jobj the object to invoke the method on
 * @param jclazz the class to use to invoke the method
 * @param methodId the id of the method to invoke
 *
 * @return the [Boolean] returned by the Java method
 */
fun CPointer<JNIEnvVar>.callNonVirtualBooleanMethod(jobj: jobject, jclazz: jclass,
                                                 methodId: jmethodID,
                                                 vararg args: CValue<jvalue>
): Boolean {
    val method = pointed.pointed?.CallNonvirtualBooleanMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callNonVirtualBooleanMethod, jobj, jclazz, methodId, ptr).toBoolean()
    }
}

/**
 * Invoke a non-virtual method on a [jobject] based on the given [jclass]
 *
 * @param jobj the object to invoke the method on
 * @param jclazz the class to use to invoke the method
 * @param methodId the id of the method to invoke
 *
 * @return the [Int] returned by the Java method
 */
fun CPointer<JNIEnvVar>.callNonVirtualIntMethod(jobj: jobject, jclazz: jclass,
                                                 methodId: jmethodID,
                                                 vararg args: CValue<jvalue>
): Int {
    val method = pointed.pointed?.CallNonvirtualIntMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callNonVirtualIntMethod, jobj, jclazz, methodId, ptr)
    }
}

/**
 * Invoke a non-virtual method on a [jobject] based on the given [jclass]
 *
 * @param jobj the object to invoke the method on
 * @param jclazz the class to use to invoke the method
 * @param methodId the id of the method to invoke
 *
 * @return the [Long] returned by the Java method
 */
fun CPointer<JNIEnvVar>.callNonVirtualLongMethod(jobj: jobject, jclazz: jclass,
                                                methodId: jmethodID,
                                                vararg args: CValue<jvalue>
): Long {
    val method = pointed.pointed?.CallNonvirtualLongMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callNonVirtualLongMethod, jobj, jclazz, methodId, ptr)
    }
}

/**
 * Invoke a non-virtual method on a [jobject] based on the given [jclass]
 *
 * @param jobj the object to invoke the method on
 * @param jclazz the class to use to invoke the method
 * @param methodId the id of the method to invoke
 *
 * @return the [Double] returned by the Java method
 */
fun CPointer<JNIEnvVar>.callNonVirtualDoubleMethod(jobj: jobject, jclazz: jclass,
                                                methodId: jmethodID,
                                                vararg args: CValue<jvalue>
): Double {
    val method = pointed.pointed?.CallNonvirtualDoubleMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callNonVirtualDoubleMethod, jobj, jclazz, methodId, ptr)
    }
}

/**
 * Invoke a non-virtual method on a [jobject] based on the given [jclass]
 *
 * @param jobj the object to invoke the method on
 * @param jclazz the class to use to invoke the method
 * @param methodId the id of the method to invoke
 *
 * @return the [Float] returned by the Java method
 */
fun CPointer<JNIEnvVar>.callNonVirtualFloatMethod(jobj: jobject, jclazz: jclass,
                                                methodId: jmethodID,
                                                vararg args: CValue<jvalue>
): Float {
    val method = pointed.pointed?.CallNonvirtualFloatMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callNonVirtualFloatMethod, jobj, jclazz, methodId, ptr)
    }
}

/**
 * Invoke a non-virtual method on a [jobject] based on the given [jclass]
 *
 * @param jobj the object to invoke the method on
 * @param jclazz the class to use to invoke the method
 * @param methodId the id of the method to invoke
 *
 * @return the [Short] returned by the Java method
 */
fun CPointer<JNIEnvVar>.callNonVirtualShortMethod(jobj: jobject, jclazz: jclass,
                                                methodId: jmethodID,
                                                vararg args: CValue<jvalue>
): Short {
    val method = pointed.pointed?.CallNonvirtualShortMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callNonVirtualShortMethod, jobj, jclazz, methodId, ptr)
    }
}

/**
 * Invoke a non-virtual method on a [jobject] based on the given [jclass]
 *
 * @param jobj the object to invoke the method on
 * @param jclazz the class to use to invoke the method
 * @param methodId the id of the method to invoke
 *
 * @return the [Byte] returned by the Java method
 */
fun CPointer<JNIEnvVar>.callNonVirtualByteMethod(jobj: jobject, jclazz: jclass,
                                                methodId: jmethodID,
                                                vararg args: CValue<jvalue>
): Byte {
    val method = pointed.pointed?.CallNonvirtualByteMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callNonVirtualByteMethod, jobj, jclazz, methodId, ptr)
    }
}

/**
 * Invoke a non-virtual method on a [jobject] based on the given [jclass]
 *
 * @param jobj the object to invoke the method on
 * @param jclazz the class to use to invoke the method
 * @param methodId the id of the method to invoke
 *
 * @return the [Char] returned by the Java method
 */
fun CPointer<JNIEnvVar>.callNonVirtualCharMethod(jobj: jobject, jclazz: jclass,
                                                 methodId: jmethodID,
                                                 vararg args: CValue<jvalue>
): Char {
    val method = pointed.pointed?.CallNonvirtualCharMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callNonVirtualCharMethod, jobj, jclazz, methodId, ptr).toInt().toChar()
    }
}
