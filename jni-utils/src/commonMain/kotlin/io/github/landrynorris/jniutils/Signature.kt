package io.github.landrynorris.jniutils

import platform.android.jstring
import kotlin.reflect.KClass

data class Signature(val parameterClasses: List<JClass>, val returnClass: JClass = Void) {
    override fun toString(): String {
        return "(${parameterClasses.joinToString(separator = "")})$returnClass"
    }
}

inline fun classToSignature(clazz: KClass<*>): JClass = when(clazz) {
    Unit::class -> Void
    kotlin.Byte::class -> Byte
    kotlin.Boolean::class -> Boolean
    kotlin.Short::class -> Short
    kotlin.Char::class -> Char
    kotlin.Int::class -> Int
    kotlin.Long::class -> Long
    kotlin.Float::class -> Float
    kotlin.Double::class -> Double
    else -> createSignature(clazz.qualifiedName ?: "")
}


