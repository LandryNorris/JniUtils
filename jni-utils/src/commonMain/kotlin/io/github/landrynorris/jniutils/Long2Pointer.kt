package io.github.landrynorris.jniutils

import kotlinx.cinterop.CPointed
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.toCPointer

inline fun <reified T: Any> Long.pointed(): T? {
    val ptr = toCPointer<CPointed>()?.asStableRef<T>()
    return ptr?.get()
}