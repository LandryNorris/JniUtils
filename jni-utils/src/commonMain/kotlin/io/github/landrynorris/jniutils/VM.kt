package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.JNIEnvVar
import platform.android.JNI_VERSION_1_6
import platform.android.JavaVM
import platform.android.JavaVMVar

fun CPointer<JNIEnvVar>.getJavaVM(): JavaVM? {
    val method = pointed.pointed?.GetJavaVM ?: error("JNI is not Oracle Standard")
    return memScoped {
        val vmPtrPtr = allocPointerTo<JavaVMVar>()
        method.invoke(this@getJavaVM, vmPtrPtr.ptr)
        vmPtrPtr.pointed?.value
    }
}

fun CPointer<JavaVMVar>.env(): CPointer<JNIEnvVar>? = memScoped {
    val method = pointed.pointed?.GetEnv ?: error("JNI is not Oracle standard")

    println("Getting env")
    val envPtr = allocPointerTo<JNIEnvVar>()
    val result = method(this@env, envPtr.reinterpret(), JNI_VERSION_1_6)
    println("Got env. Result is $result")

    envPtr.value
}
