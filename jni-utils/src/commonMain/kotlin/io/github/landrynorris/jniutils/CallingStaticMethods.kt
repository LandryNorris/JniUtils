package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.*

fun CPointer<JNIEnvVar>.getStaticMethodId(clazz: jclass, name: String, signature: String): jmethodID? {
    val method = pointed.pointed?.GetStaticMethodID ?: error("JNI is not Oracle standard")
    return name.encodeToByteArray().usePinned { pinnedName ->
        signature.encodeToByteArray().usePinned { pinnedSig ->
            method.invoke(this, clazz, pinnedName.addressOf(0),
                pinnedSig.addressOf(0))
        }
    }
}

fun CPointer<JNIEnvVar>.getStaticObjectMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): jobject? {
    val method = pointed.pointed?.CallStaticObjectMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@getStaticObjectMethod, clazz, methodId, ptr)
    }
}

fun CPointer<JNIEnvVar>.getStaticIntMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): Int {
    val method = pointed.pointed?.CallStaticIntMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@getStaticIntMethod, clazz, methodId, ptr)
    }
}

fun CPointer<JNIEnvVar>.getStaticLongMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): Long {
    val method = pointed.pointed?.CallStaticLongMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@getStaticLongMethod, clazz, methodId, ptr)
    }
}

fun CPointer<JNIEnvVar>.getStaticDoubleMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): Double {
    val method = pointed.pointed?.CallStaticDoubleMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@getStaticDoubleMethod, clazz, methodId, ptr)
    }
}

fun CPointer<JNIEnvVar>.getStaticFloatMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): Float {
    val method = pointed.pointed?.CallStaticFloatMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@getStaticFloatMethod, clazz, methodId, ptr)
    }
}

fun CPointer<JNIEnvVar>.getStaticShortMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): Short {
    val method = pointed.pointed?.CallStaticShortMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@getStaticShortMethod, clazz, methodId, ptr)
    }
}

fun CPointer<JNIEnvVar>.getStaticCharMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): Char {
    val method = pointed.pointed?.CallStaticCharMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@getStaticCharMethod, clazz, methodId, ptr)
    }.toInt().toChar()
}

fun CPointer<JNIEnvVar>.getStaticByteMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): Byte {
    val method = pointed.pointed?.CallStaticByteMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@getStaticByteMethod, clazz, methodId, ptr)
    }
}

fun CPointer<JNIEnvVar>.getStaticBooleanMethod(clazz: jclass, methodId: jmethodID, vararg args: jvalue): Boolean {
    val method = pointed.pointed?.CallStaticBooleanMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@getStaticBooleanMethod, clazz, methodId, ptr)
    }.toBoolean()
}
