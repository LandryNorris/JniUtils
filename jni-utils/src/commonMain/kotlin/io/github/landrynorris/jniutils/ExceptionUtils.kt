package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.JNIEnvVar
import platform.android.jclass
import platform.android.jthrowable

fun CPointer<JNIEnvVar>.throwException(obj: jthrowable): Boolean {
    val method = pointed.pointed?.Throw ?: error("JNI is not Oracle standard")
    return method.invoke(this, obj) == 0 //Returns 0 if successful
}

fun CPointer<JNIEnvVar>.throwNew(jclazz: jclass, message: String): Boolean {
    val method = pointed.pointed?.ThrowNew ?: error("JNI is not Oracle standard")
    return message.encodeToByteArray().usePinned {
        method.invoke(this, jclazz, it.addressOf(0)) == 0 //Returns 0 if successful
    }
}

fun CPointer<JNIEnvVar>.exceptionOccurred(): jthrowable? {
    val method = pointed.pointed?.ExceptionOccurred ?: error("JNI is not Oracle standard")
    return method.invoke(this)
}

fun CPointer<JNIEnvVar>.describeException() {
    val method = pointed.pointed?.ExceptionDescribe ?: error("JNI is not Oracle standard")
    return method.invoke(this)
}

fun CPointer<JNIEnvVar>.clearException() {
    val method = pointed.pointed?.ExceptionClear ?: error("JNI is not Oracle standard")
    return method.invoke(this)
}

fun CPointer<JNIEnvVar>.exceptionCheck(): Boolean {
    val method = pointed.pointed?.ExceptionCheck ?: error("JNI is not Oracle standard")
    return method.invoke(this).toBoolean()
}

fun CPointer<JNIEnvVar>.fatalError(message: String) {
    val method = pointed.pointed?.FatalError ?: error("JNI is not Oracle standard")
    message.encodeToByteArray().usePinned {
        method.invoke(this, it.addressOf(0))
    }
}
