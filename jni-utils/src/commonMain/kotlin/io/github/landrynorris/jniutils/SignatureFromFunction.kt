package io.github.landrynorris.jniutils

import kotlinx.cinterop.CPointer
import platform.android.JNIEnvVar
import platform.android.jobject
import kotlin.reflect.typeOf

inline fun <reified R> signature(function: (CPointer<JNIEnvVar>, jobject) -> R): String {
    if(R::class.isCPointer()) error("CPointer classes are not currently supported")
    return Signature(listOf(), classToSignature(typeOf<R>())).toString()
}

inline fun <reified R, reified T> signature(function: (CPointer<JNIEnvVar>, jobject, T) -> R): String {
    if(T::class.isCPointer() || R::class.isCPointer())
        error("CPointer classes are not currently supported")
    return Signature(listOf(classToSignature(typeOf<T>())),
        classToSignature(typeOf<R>())).toString()
}

inline fun <reified R, reified T, reified S>
        signature(function: (CPointer<JNIEnvVar>, jobject, T, S) -> R): String {
    if(T::class.isCPointer() || R::class.isCPointer() || S::class.isCPointer())
        error("CPointer classes are not currently supported")
    return Signature(listOf(typeOf<T>(), typeOf<S>()).map { classToSignature(it) },
        classToSignature(typeOf<R>())).toString()
}

inline fun <reified R, reified T, reified S, reified U>
        signature(function: (CPointer<JNIEnvVar>, jobject, T, S, U) -> R): String {
    if(T::class.isCPointer() || R::class.isCPointer() || S::class.isCPointer()
        || U::class.isCPointer())
        error("CPointer classes are not currently supported")
    return Signature(listOf(typeOf<T>(), typeOf<S>(), typeOf<U>()).map { classToSignature(it) },
        classToSignature(typeOf<R>())
    ).toString()
}

inline fun <reified R, reified T, reified S, reified U, reified V>
        signature(function: (CPointer<JNIEnvVar>, jobject, T, S, U, V) -> R): String {
    if(T::class.isCPointer() || R::class.isCPointer() || S::class.isCPointer()
        || U::class.isCPointer() || V::class.isCPointer())
        error("CPointer classes are not currently supported")
    return Signature(listOf(typeOf<T>(), typeOf<S>(), typeOf<U>(), typeOf<V>()).map { classToSignature(it) },
        classToSignature(typeOf<R>())
    ).toString()
}

inline fun <reified R, reified T, reified S, reified U, reified V, reified W>
        signature(function: (CPointer<JNIEnvVar>, jobject, T, S, U, V, W) -> R): String {
    if(T::class.isCPointer() || R::class.isCPointer() || S::class.isCPointer()
        || U::class.isCPointer() || V::class.isCPointer() || W::class.isCPointer())
        error("CPointer classes are not currently supported")
    return Signature(listOf(typeOf<T>(), typeOf<S>(), typeOf<U>(), typeOf<V>(), typeOf<W>()).map { classToSignature(it) },
        classToSignature(typeOf<R>())
    ).toString()
}

inline fun <reified R, reified T, reified S, reified U, reified V, reified W, reified X>
        signature(function: (CPointer<JNIEnvVar>, jobject, T, S, U, V, W, X) -> R): String {
    if(T::class.isCPointer() || R::class.isCPointer() || S::class.isCPointer()
        || U::class.isCPointer() || V::class.isCPointer() || W::class.isCPointer()
        || X::class.isCPointer())
        error("CPointer classes are not currently supported")
    return Signature(listOf(typeOf<T>(), typeOf<S>(), typeOf<U>(), typeOf<V>(), typeOf<W>(), typeOf<X>()).map { classToSignature(it) },
        classToSignature(typeOf<R>())
    ).toString()
}

inline fun <reified R, reified T, reified S, reified U, reified V, reified W, reified X, reified Y>
        signature(function: (CPointer<JNIEnvVar>, jobject, T, S, U, V, W, X, Y) -> R): String {
    if(T::class.isCPointer() || R::class.isCPointer() || S::class.isCPointer()
        || U::class.isCPointer() || V::class.isCPointer() || W::class.isCPointer()
        || X::class.isCPointer() || Y::class.isCPointer())
        error("CPointer classes are not currently supported")
    return Signature(listOf(typeOf<T>(), typeOf<S>(), typeOf<U>(), typeOf<V>(), typeOf<W>(), typeOf<X>(), typeOf<Y>())
        .map { classToSignature(it) },
        classToSignature(typeOf<R>())
    ).toString()
}
