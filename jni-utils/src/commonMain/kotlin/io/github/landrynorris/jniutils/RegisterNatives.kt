package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.JNIEnvVar
import platform.android.JNINativeMethod
import platform.android.jclass

fun CPointer<JNIEnvVar>.registerNatives(clazz: jclass, methods: List<JNINativeMethod>) = memScoped {
    val method = pointed.pointed?.RegisterNatives ?: error("JNI is not Oracle standard")
    val methodBuffer = allocArray<JNINativeMethod>(methods.size) {
        val m = methods[it]
        name = m.name
        signature = m.signature
        fnPtr = m.fnPtr
    }
    method.invoke(this@registerNatives, clazz, methodBuffer, methods.size)
}

fun CPointer<JNIEnvVar>.unregisterNatives(clazz: jclass) {
    val method = pointed.pointed?.UnregisterNatives ?: error("JNI is not Oracle standard")
    method.invoke(this, clazz)
}

fun CPointer<JNIEnvVar>.registerNatives(block: JNIMethodScope.() -> Unit) {
    val scope = JNIMethodScope()
    scope.block()
    if(scope.clazz == null) error("Class must be set. Make sure to use 'clazz = ...' in the config block.")
    memScoped {
        registerNatives(scope.clazz!!, scope.methods.map { it.toNativeMethod(this) })
    }
}

data class JNIMethod(var name: String = "", var signature: String = "", var function: CPointer<*>? = null)

private fun JNIMethod.toNativeMethod(memScope: MemScope): JNINativeMethod {
    val native = memScope.alloc<JNINativeMethod>()
    native.name = name.cstr.getPointer(memScope)
    native.signature = signature.cstr.getPointer(memScope)
    native.fnPtr = function
    return native
}

class JNIMethodScope {
    val methods = arrayListOf<JNIMethod>()

    var clazz: jclass? = null

    fun method(name: String, block: JNIMethod.() -> Unit) {
        val method = JNIMethod()
        method.block()
        methods.add(method)
    }
}
