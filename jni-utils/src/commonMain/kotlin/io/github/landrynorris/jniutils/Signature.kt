package io.github.landrynorris.jniutils

import platform.android.jobject
import platform.android.jstring
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.typeOf

data class Signature(val parameterClasses: List<JClass>, val returnClass: JClass = Void) {
    override fun toString(): String {
        return "(${parameterClasses.joinToString(separator = "")})$returnClass"
    }
}

inline fun <reified T> signature() = classToSignature(typeOf<T>())

inline fun classToSignature(type: KType) = when(type) {
    typeOf<Unit>() -> Void
    typeOf<Byte>() -> Byte
    typeOf<Boolean>() -> Boolean
    typeOf<Short>() -> Short
    typeOf<Char>() -> Char
    typeOf<Int>() -> Int
    typeOf<Long>() -> Long
    typeOf<Float>() -> Float
    typeOf<Double>() -> Double
    typeOf<BooleanArray>() -> BooleanArray
    typeOf<ByteArray>() -> ByteArray
    typeOf<ShortArray>() -> ShortArray
    typeOf<CharArray>() -> CharArray
    typeOf<IntArray>() -> IntArray
    typeOf<FloatArray>() -> FloatArray
    typeOf<DoubleArray>() -> DoubleArray
    typeOf<jobject>() -> createSignature("java.lang.Object")
    typeOf<jstring>() -> String
    else -> createSignature(type.toString())
}
