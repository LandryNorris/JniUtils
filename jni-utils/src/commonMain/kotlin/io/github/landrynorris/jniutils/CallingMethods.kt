package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.*

fun CPointer<JNIEnvVar>.getMethodId(clazz: jclass, name: String, sig: String): jmethodID? {
    val method = pointed.pointed?.GetMethodID ?: error("JNI is not Oracle standard")
    return name.encodeToByteArray().usePinned { pinnedName ->
        sig.encodeToByteArray().usePinned { pinnedSig ->
            method.invoke(this, clazz, pinnedName.addressOf(0),
                pinnedSig.addressOf(0))
        }
    }
}

fun CPointer<JNIEnvVar>.callVoidMethod(jobj: jobject?,
                                  methodId: jmethodID,
                                         vararg args: CValue<jvalue>) {
    val method = pointed.pointed?.CallVoidMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callVoidMethod, jobj, methodId, ptr)
    }
}

fun CPointer<JNIEnvVar>.callObjectMethod(jobj: jobject?,
                                       methodId: jmethodID,
                                         vararg args: CValue<jvalue>): jobject? {
    val method = pointed.pointed?.CallObjectMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callObjectMethod, jobj, methodId, ptr)
    }
}

fun CPointer<JNIEnvVar>.callBooleanMethod(jobj: jobject?,
                                         methodId: jmethodID,
                                         vararg args: CValue<jvalue>): Boolean {
    val method = pointed.pointed?.CallBooleanMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callBooleanMethod, jobj, methodId, ptr).toBoolean()
    }
}

fun CPointer<JNIEnvVar>.callIntMethod(jobj: jobject?,
                                          methodId: jmethodID,
                                         vararg args: CValue<jvalue>): Int {
    val method = pointed.pointed?.CallIntMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callIntMethod, jobj, methodId, ptr)
    }
}

fun CPointer<JNIEnvVar>.callFloatMethod(jobj: jobject?,
                                          methodId: jmethodID,
                                         vararg args: CValue<jvalue>): Float {
    val method = pointed.pointed?.CallFloatMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callFloatMethod, jobj, methodId, ptr)
    }
}

fun CPointer<JNIEnvVar>.callDoubleMethod(jobj: jobject?,
                                         methodId: jmethodID,
                                         vararg args: CValue<jvalue>): Double {
    val method = pointed.pointed?.CallDoubleMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        method.invoke(this@callDoubleMethod, jobj, methodId, args.toCArray(this))
    }
}

fun CPointer<JNIEnvVar>.callCharMethod(jobj: jobject?,
                                         methodId: jmethodID,
                                         vararg args: CValue<jvalue>): Char {
    val method = pointed.pointed?.CallCharMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callCharMethod, jobj, methodId, ptr).toInt().toChar()
    }
}

fun CPointer<JNIEnvVar>.callShortMethod(jobj: jobject?,
                                         methodId: jmethodID,
                                         vararg args: CValue<jvalue>): Short {
    val method = pointed.pointed?.CallShortMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callShortMethod, jobj, methodId, ptr)
    }
}

fun CPointer<JNIEnvVar>.callByteMethod(jobj: jobject?,
                                         methodId: jmethodID,
                                         vararg args: CValue<jvalue>): Byte {
    val method = pointed.pointed?.CallByteMethodA ?: error("JNI is not Oracle standard")
    return memScoped {
        val ptr = args.toCArray(this)
        method.invoke(this@callByteMethod, jobj, methodId, ptr)
    }
}
