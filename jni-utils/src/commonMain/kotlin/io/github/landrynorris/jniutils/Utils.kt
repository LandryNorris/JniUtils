package io.github.landrynorris.jniutils

import kotlinx.cinterop.*
import platform.android.JNI_TRUE
import platform.android.jboolean
import platform.android.jvalue

fun jboolean.toBoolean(): Boolean = (this == JNI_TRUE.toUByte())

val Double.jvalue: CValue<jvalue> get() {
    val value = this
    return cValue<jvalue> { d = value }
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
