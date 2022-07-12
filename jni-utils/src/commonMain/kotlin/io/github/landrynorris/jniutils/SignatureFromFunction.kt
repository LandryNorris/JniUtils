package io.github.landrynorris.jniutils

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.staticCFunction
import platform.android.JNIEnvVar
import platform.android.jobject

inline fun <reified R> signature(function: (CPointer<JNIEnvVar>, jobject) -> R): String {
    return Signature(listOf(), classToSignature(R::class)).toString()
}

inline fun <reified R, reified T> signature(function: (CPointer<JNIEnvVar>, jobject, T) -> R): String {
    return Signature(listOf(classToSignature(T::class)),
        classToSignature(R::class)).toString()
}

inline fun <reified R, reified T, reified S>
        signature(function: (CPointer<JNIEnvVar>, jobject, T, S) -> R): String {
    return Signature(listOf(T::class, S::class).map { classToSignature(it) },
        classToSignature(R::class)).toString()
}

inline fun <reified R, reified T, reified S, reified U>
        signature(function: (CPointer<JNIEnvVar>, jobject, T, S, U) -> R): String {
    return Signature(listOf(T::class, S::class, U::class).map { classToSignature(it) },
        classToSignature(R::class)
    ).toString()
}

inline fun <reified R, reified T, reified S, reified U, reified V>
        signature(function: (CPointer<JNIEnvVar>, jobject, T, S, U, V) -> R): String {
    return Signature(listOf(T::class, S::class, U::class, V::class).map { classToSignature(it) },
        classToSignature(R::class)
    ).toString()
}

inline fun <reified R, reified T, reified S, reified U, reified V, reified W>
        signature(function: (CPointer<JNIEnvVar>, jobject, T, S, U, V, W) -> R): String {
    return Signature(listOf(T::class, S::class, U::class, V::class, W::class).map { classToSignature(it) },
        classToSignature(R::class)
    ).toString()
}

inline fun <reified R, reified T, reified S, reified U, reified V, reified W, reified X>
        signature(function: (CPointer<JNIEnvVar>, jobject, T, S, U, V, W, X) -> R): String {
    return Signature(listOf(T::class, S::class, U::class, V::class, W::class, X::class).map { classToSignature(it) },
        classToSignature(R::class)
    ).toString()
}

inline fun <reified R, reified T, reified S, reified U, reified V, reified W, reified X, reified Y>
        signature(function: (CPointer<JNIEnvVar>, jobject, T, S, U, V, W, X, Y) -> R): String {
    return Signature(listOf(T::class, S::class, U::class, V::class, W::class, X::class, Y::class)
        .map { classToSignature(it) },
        classToSignature(R::class)
    ).toString()
}
