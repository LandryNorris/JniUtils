package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.*
import kotlin.reflect.KClass

inline fun <T: KClass<*>> T.isCPointer(): Boolean {
    return this == CPointer::class
}

fun jboolean.toBoolean(): Boolean = (this == JNI_TRUE.toUByte())

fun Boolean.toJBoolean(): jboolean = if(this) JNI_TRUE.toUByte() else JNI_FALSE.toUByte()

fun Byte.jvalue(scope: NativePlacement): jvalue {
    return scope.alloc<jvalue>().also { it.b = this }
}

fun Short.jvalue(scope: NativePlacement): jvalue {
    return scope.alloc<jvalue>().also { it.s = this }
}

fun Char.jvalue(scope: NativePlacement): jvalue {
    return scope.alloc<jvalue>().also { it.c = code.toUShort() }
}

fun Int.jvalue(scope: NativePlacement): jvalue {
    return scope.alloc<jvalue>().also { it.i = this }
}

fun Long.jvalue(scope: NativePlacement): jvalue {
    return scope.alloc<jvalue>().also { it.j = this }
}

fun jobject?.jvalue(scope: NativePlacement): jvalue {
    return scope.alloc<jvalue>().also { it.l = this }
}

fun Float.jvalue(scope: NativePlacement): jvalue {
    return scope.alloc<jvalue>().also { it.f = this }
}

fun Double.jvalue(scope: NativePlacement): jvalue {
    return scope.alloc<jvalue>().also { it.d = this }
}

fun jobject.jvalue(scope: NativePlacement): jvalue {
    return scope.alloc<jvalue>().also { it.l = this }
}

fun Array<out CValue<jvalue>>.toCArray(memScope: NativePlacement): CArrayPointer<jvalue> {
    val arr = this
    return memScope.allocArray(size) { index ->
        d = arr[index].useContents { d }
    }
}

/**
 * This allocates a new array. Free it when done.
 */
fun Array<out jvalue>.toCArray(memScope: NativePlacement): CArrayPointer<jvalue> {
    val arr = this
    println("Array being copied: ${arr.joinToString(", ") { it.d.toString() }}")
    return memScope.allocArray(size) { index ->
        println("Array after copy: ${arr.joinToString(", ") { it.d.toString() }}")
        println("Copying data over: ${arr[index].d}")
        d = arr[index].d //it's a union, so setting the longest field sets all fields.
    }
}
