package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.*

fun CPointer<JNIEnvVar>.getStringUtfChars(value: jstring, isCopy: CPointer<jbooleanVar>? = null):
        CPointer<ByteVar>? {
    val method = pointed.pointed?.GetStringUTFChars ?: error("JNI is not Oracle standard")
    return method.invoke(this, value, isCopy)
}

fun CPointer<JNIEnvVar>.getVersion(): Int {
    val method = pointed.pointed?.GetVersion ?: error("JNI is not Oracle standard")
    return method.invoke(this)
}

fun CPointer<JNIEnvVar>.defineClass(name: String, loader: jobject,
                                    buf: CPointer<jbyteVar>, size: Int): jclass? {
    val method = pointed.pointed?.DefineClass ?: error("JNI is not Oracle standard")
    return name.encodeToByteArray().usePinned {
        method.invoke(this, it.addressOf(0), loader, buf, size)
    }
}

fun CPointer<JNIEnvVar>.findClass(name: String): jclass? {
    val method = pointed.pointed?.FindClass ?: error("JNI is not Oracle standard")
    return name.encodeToByteArray().usePinned {
        method.invoke(this, it.addressOf(0))
    }
}

fun CPointer<JNIEnvVar>.findClassOrThrow(name: String): jclass {
    return findClass(name) ?: error("Class named $name not found.")
}

fun CPointer<JNIEnvVar>.getSuperclass(name: String): jclass? {
    val method = pointed.pointed?.GetSuperclass ?: error("JNI is not Oracle standard")
    return name.encodeToByteArray().usePinned {
        method.invoke(this, it.addressOf(0))
    }
}

fun CPointer<JNIEnvVar>.getSuperclassOrThrow(name: String): jclass {
    return getSuperclass(name) ?: error("Class named $name not found.")
}

fun CPointer<JNIEnvVar>.isAssignableFrom(jclass1: jclass, jclass2: jclass): Boolean {
    val method = pointed.pointed?.IsAssignableFrom ?: error("JNI is not Oracle standard")
    return method.invoke(this, jclass1, jclass2).toBoolean()
}

fun CPointer<JNIEnvVar>.ensureLocalCapacity(capacity: Int): Int {
    val method = pointed.pointed?.EnsureLocalCapacity ?: error("JNI is not Oracle standard")
    return method.invoke(this, capacity)
}

fun CPointer<JNIEnvVar>.pushLocalFrame(capacity: Int): Int {
    val method = pointed.pointed?.PushLocalFrame ?: error("JNI is not Oracle standard")
    return method.invoke(this, capacity)
}

fun CPointer<JNIEnvVar>.popLocalFrame(result: jobject?): jobject? {
    val method = pointed.pointed?.PopLocalFrame ?: error("JNI is not Oracle standard")
    return method.invoke(this, result)
}

fun CPointer<JNIEnvVar>.allocObject(jclazz: jclass?): jobject? {
    val method = pointed.pointed?.AllocObject ?: error("JNI is not Oracle standard")
    return method.invoke(this, jclazz)
}

fun CPointer<JNIEnvVar>.newObject(jclazz: jclass?,
                                   methodId: jmethodID, vararg args: jvalue): jobject? {
    val method = pointed.pointed?.NewObjectA ?: error("JNI is not Oracle standard")
    return memScoped {
        method.invoke(this@newObject, jclazz, methodId, args.toCArray(this))
    }
}

fun CPointer<JNIEnvVar>.getObjectClass(obj: jobject): jobject? {
    val method = pointed.pointed?.GetObjectClass ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj)
}

fun CPointer<JNIEnvVar>.getObjectRefType(obj: jobject): jobjectRefType {
    val method = pointed.pointed?.GetObjectRefType ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj)
}

fun CPointer<JNIEnvVar>.isInstanceOf(obj: jobject?, clazz: jclass): Boolean {
    val method = pointed.pointed?.IsInstanceOf ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, clazz).toBoolean()
}

fun CPointer<JNIEnvVar>.isSameObject(obj: jobject?, obj2: jobject?): Boolean {
    val method = pointed.pointed?.IsSameObject ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj, obj2).toBoolean()
}

fun CPointer<JNIEnvVar>.getFieldId(clazz: jclass?, name: String, sig: String): jfieldID? {
    val method = pointed.pointed?.GetFieldID ?: error("JNI is not Oracle standard")
    return name.encodeToByteArray().usePinned { pinnedName ->
        sig.encodeToByteArray().usePinned { pinnedSig ->
            method.invoke(this, clazz, pinnedName.addressOf(0),
                pinnedSig.addressOf(0))
        }
    }
}
