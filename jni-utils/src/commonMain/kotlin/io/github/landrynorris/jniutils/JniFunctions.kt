package io.github.landrynorris.jniutils

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.staticCFunction
import platform.android.JNIEnvVar
import platform.android.jobject

inline fun <reified R> JNIMethodScope.method(name: String, crossinline function: (CPointer<JNIEnvVar>, jobject) -> R) {
    val method = JNIMethod()
    method.name = name
    method.function = staticCFunction { env: CPointer<JNIEnvVar>, jobj: jobject ->
        function(env, jobj)
    }
    method.signature = Signature(listOf(), classToSignature(R::class)).toString()
    methods.add(method)
}

inline fun <reified R, reified T> JNIMethodScope.method(name: String, crossinline function: (CPointer<JNIEnvVar>, jobject, T) -> R) {
    val method = JNIMethod()
    method.name = name
    method.function = staticCFunction { env: CPointer<JNIEnvVar>, jobj: jobject, p1: T ->
        function(env, jobj, p1)
    }
    method.signature = Signature(listOf(classToSignature(T::class)),
        classToSignature(R::class)).toString()
    methods.add(method)
}

inline fun <reified R, reified T, reified S>
        JNIMethodScope.method(name: String, crossinline function: (CPointer<JNIEnvVar>, jobject, T, S) -> R) {
    val method = JNIMethod()
    method.name = name
    method.function = staticCFunction { env: CPointer<JNIEnvVar>, jobj: jobject, p1: T, p2: S ->
        function(env, jobj, p1, p2)
    }
    method.signature = Signature(listOf(T::class, S::class).map { classToSignature(it) },
        classToSignature(R::class)
    ).toString()
    methods.add(method)
}

inline fun <reified R, reified T, reified S, reified U>
        JNIMethodScope.method(name: String, crossinline function: (CPointer<JNIEnvVar>, jobject, T, S, U) -> R) {
    val method = JNIMethod()
    method.name = name
    method.function = staticCFunction { env: CPointer<JNIEnvVar>, jobj: jobject, p1: T, p2: S, p3: U ->
        function(env, jobj, p1, p2, p3)
    }
    method.signature = Signature(listOf(T::class, S::class, U::class).map { classToSignature(it) },
        classToSignature(R::class)
    ).toString()
    methods.add(method)
}

inline fun <reified R, reified T, reified S, reified U, reified V>
        JNIMethodScope.method(name: String, crossinline function: (CPointer<JNIEnvVar>, jobject, T, S, U, V) -> R) {
    val method = JNIMethod()
    method.name = name
    method.function = staticCFunction { env: CPointer<JNIEnvVar>, jobj: jobject, p1: T, p2: S, p3: U, p4: V ->
        function(env, jobj, p1, p2, p3, p4)
    }
    method.signature = Signature(listOf(T::class, S::class, U::class, V::class).map { classToSignature(it) },
        classToSignature(R::class)
    ).toString()
    methods.add(method)
}

inline fun <reified R, reified T, reified S, reified U, reified V, reified W>
        JNIMethodScope.method(name: String, crossinline function: (CPointer<JNIEnvVar>, jobject, T, S, U, V, W) -> R) {
    val method = JNIMethod()
    method.name = name
    method.function = staticCFunction { env: CPointer<JNIEnvVar>, jobj: jobject, p1: T, p2: S, p3: U, p4: V, p5: W ->
        function(env, jobj, p1, p2, p3, p4, p5)
    }
    method.signature = Signature(listOf(T::class, S::class, U::class, V::class, W::class).map { classToSignature(it) },
        classToSignature(R::class)
    ).toString()
    methods.add(method)
}

inline fun <reified R, reified T, reified S, reified U, reified V, reified W, reified X>
        JNIMethodScope.method(name: String, crossinline function: (CPointer<JNIEnvVar>, jobject, T, S, U, V, W, X) -> R) {
    val method = JNIMethod()
    method.name = name
    method.function = staticCFunction { env: CPointer<JNIEnvVar>, jobj: jobject, p1: T, p2: S, p3: U, p4: V, p5: W, p6: X ->
        function(env, jobj, p1, p2, p3, p4, p5, p6)
    }
    method.signature = Signature(listOf(T::class, S::class, U::class, V::class, W::class, X::class).map { classToSignature(it) },
        classToSignature(R::class)
    ).toString()
    methods.add(method)
}

inline fun <reified R, reified T, reified S, reified U, reified V, reified W, reified X, reified Y>
        JNIMethodScope.method(name: String, crossinline function: (CPointer<JNIEnvVar>, jobject, T, S, U, V, W, X, Y) -> R) {
    val method = JNIMethod()
    method.name = name
    method.function = staticCFunction { env: CPointer<JNIEnvVar>, jobj: jobject, p1: T, p2: S, p3: U, p4: V, p5: W, p6: X, p7: Y ->
        function(env, jobj, p1, p2, p3, p4, p5, p6, p7)
    }
    method.signature = Signature(listOf(T::class, S::class, U::class, V::class, W::class, X::class, Y::class).map { classToSignature(it) },
        classToSignature(R::class)
    ).toString()
    methods.add(method)
}
