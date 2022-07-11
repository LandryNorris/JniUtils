package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.*

/**
 * Get the [jmethodID] associated with the given [jclass], name, and signature
 *
 * @param clazz the Java class that has the instance method
 * @param name the name of the method
 * @param signature the signature of the method
 *
 * @return a [jmethodID] that identifies this method to the JVM
 */
fun CPointer<JNIEnvVar>.getMethodId(clazz: jclass, name: String, signature: String): jmethodID? {
    val method = pointed.pointed?.GetMethodID ?: error("JNI is not Oracle standard")
    return name.encodeToByteArray().usePinned { pinnedName ->
        signature.encodeToByteArray().usePinned { pinnedSig ->
            method.invoke(this, clazz, pinnedName.addressOf(0),
                pinnedSig.addressOf(0))
        }
    }
}

/**
 * Call an instance method returning Void (Unit in Kotlin) on a [jobject] with the given [jmethodID] and the
 * given parameters.
 */
fun CPointer<JNIEnvVar>.callVoidMethod(jobj: jobject,
                                       methodId: jmethodID,
                                       vararg args: jvalue) {
    val method = pointed.pointed?.CallVoidMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callVoidMethod, jobj, methodId, ptr)
    }
}

/**
 * Call an instance method returning a Java Object on a [jobject] with the given [jmethodID] and the
 * given parameters.
 *
 * @return the Java Object returned by the Java method
 */
fun CPointer<JNIEnvVar>.callObjectMethod(jobj: jobject,
                                       methodId: jmethodID,
                                         vararg args: jvalue): jobject? {
    val method = pointed.pointed?.CallObjectMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callObjectMethod, jobj, methodId, ptr)
    }
}


/**
 * Call an instance method returning a Boolean on a [jobject] with the given [jmethodID] and the
 * given parameters.
 *
 * @return the Boolean returned by the Java method
 */
fun CPointer<JNIEnvVar>.callBooleanMethod(jobj: jobject,
                                         methodId: jmethodID,
                                         vararg args: jvalue): Boolean {
    val method = pointed.pointed?.CallBooleanMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callBooleanMethod, jobj, methodId, ptr).toBoolean()
    }
}

/**
 * Call an instance method returning an Int on a [jobject] with the given [jmethodID] and the
 * given parameters.
 *
 * @return the Int returned by the Java method
 */
fun CPointer<JNIEnvVar>.callIntMethod(jobj: jobject,
                                          methodId: jmethodID,
                                         vararg args: jvalue): Int {
    val method = pointed.pointed?.CallIntMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callIntMethod, jobj, methodId, ptr)
    }
}

/**
 * Call an instance method returning a Float on a [jobject] with the given [jmethodID] and the
 * given parameters.
 *
 * @return the Float returned by the Java method
 */
fun CPointer<JNIEnvVar>.callFloatMethod(jobj: jobject,
                                          methodId: jmethodID,
                                         vararg args: jvalue): Float {
    val method = pointed.pointed?.CallFloatMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callFloatMethod, jobj, methodId, ptr)
    }
}


/**
 * Call an instance method returning a Double on a [jobject] with the given [jmethodID] and the
 * given parameters.
 *
 * @return the Double returned by the Java method
 */
fun CPointer<JNIEnvVar>.callDoubleMethod(jobj: jobject,
                                         methodId: jmethodID,
                                         vararg args: jvalue): Double {
    val method = pointed.pointed?.CallDoubleMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        method.invoke(this@callDoubleMethod, jobj, methodId, args.toCArray(this))
    }
}

/**
 * Call an instance method returning a Char on a [jobject] with the given [jmethodID] and the
 * given parameters.
 *
 * @return the Char returned by the Java method
 */
fun CPointer<JNIEnvVar>.callCharMethod(jobj: jobject,
                                         methodId: jmethodID,
                                         vararg args: jvalue): Char {
    val method = pointed.pointed?.CallCharMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callCharMethod, jobj, methodId, ptr).toInt().toChar()
    }
}

/**
 * Call an instance method returning a Short on a [jobject] with the given [jmethodID] and the
 * given parameters.
 *
 * @return the Short returned by the Java method
 */
fun CPointer<JNIEnvVar>.callShortMethod(jobj: jobject,
                                         methodId: jmethodID,
                                         vararg args: jvalue): Short {
    val method = pointed.pointed?.CallShortMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callShortMethod, jobj, methodId, ptr)
    }
}

/**
 * Call an instance method returning a Byte on a [jobject] with the given [jmethodID] and the
 * given parameters.
 *
 * @return the Byte returned by the Java method
 */
fun CPointer<JNIEnvVar>.callByteMethod(jobj: jobject,
                                         methodId: jmethodID,
                                         vararg args: jvalue): Byte {
    val method = pointed.pointed?.CallByteMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callByteMethod, jobj, methodId, ptr)
    }
}
