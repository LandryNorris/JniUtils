package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.*

/**
 * Get the [jmethodID] of a static method by its name, signature and class.
 */
fun CPointer<JNIEnvVar>.getStaticMethodId(clazz: jclass, name: String, signature: String): jmethodID? {
    val method = pointed.pointed?.GetStaticMethodID ?: error("JNI is not Oracle standard")
    return name.encodeToByteArray().usePinned { pinnedName ->
        signature.encodeToByteArray().usePinned { pinnedSig ->
            method.invoke(this, clazz, pinnedName.addressOf(0),
                pinnedSig.addressOf(0))
        }
    }
}

/**
 * Call a static method on a Java class that returns an object
 */
fun CPointer<JNIEnvVar>.callStaticObjectMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): jobject? {
    val method = pointed.pointed?.CallStaticObjectMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callStaticObjectMethod, clazz, methodId, ptr)
    }
}

/**
 * Call a static method on a Java class that returns an [Int]
 */
fun CPointer<JNIEnvVar>.callStaticIntMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): Int {
    val method = pointed.pointed?.CallStaticIntMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callStaticIntMethod, clazz, methodId, ptr)
    }
}

/**
 * Call a static method on a Java class that returns a [Long]
 */
fun CPointer<JNIEnvVar>.callStaticLongMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): Long {
    val method = pointed.pointed?.CallStaticLongMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callStaticLongMethod, clazz, methodId, ptr)
    }
}

/**
 * Call a static method on a Java class that returns a [Double]
 */
fun CPointer<JNIEnvVar>.callStaticDoubleMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): Double {
    val method = pointed.pointed?.CallStaticDoubleMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callStaticDoubleMethod, clazz, methodId, ptr)
    }
}

/**
 * Call a static method on a Java class that returns a [Float]
 */
fun CPointer<JNIEnvVar>.callStaticFloatMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): Float {
    val method = pointed.pointed?.CallStaticFloatMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callStaticFloatMethod, clazz, methodId, ptr)
    }
}

/**
 * Call a static method on a Java class that returns a [Short]
 */
fun CPointer<JNIEnvVar>.callStaticShortMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): Short {
    val method = pointed.pointed?.CallStaticShortMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callStaticShortMethod, clazz, methodId, ptr)
    }
}

/**
 * Call a static method on a Java class that returns a [Char]
 */
fun CPointer<JNIEnvVar>.callStaticCharMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): Char {
    val method = pointed.pointed?.CallStaticCharMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callStaticCharMethod, clazz, methodId, ptr)
    }.toInt().toChar()
}

/**
 * Call a static method on a Java class that returns a [Byte]
 */
fun CPointer<JNIEnvVar>.callStaticByteMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): Byte {
    val method = pointed.pointed?.CallStaticByteMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callStaticByteMethod, clazz, methodId, ptr)
    }
}

/**
 * Call a static method on a Java class that returns a [Boolean]
 */
fun CPointer<JNIEnvVar>.callStaticBooleanMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): Boolean {
    val method = pointed.pointed?.CallStaticBooleanMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callStaticBooleanMethod, clazz, methodId, ptr)
    }.toBoolean()
}
