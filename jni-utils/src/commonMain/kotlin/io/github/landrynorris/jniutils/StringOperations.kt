package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.*

fun CPointer<JNIEnvVar>.newString(chars: CPointer<jcharVar>, length: Int): jstring? {
    val method = pointed.pointed?.NewString ?: error("JNI is not Oracle standard")
    return method.invoke(this, chars, length)
}

fun String.toJString(env: CPointer<JNIEnvVar>): jstring? {
    return memScoped {
        env.newString(wcstr.ptr, length)
    }
}