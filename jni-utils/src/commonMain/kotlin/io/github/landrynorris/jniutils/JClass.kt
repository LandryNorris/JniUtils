package io.github.landrynorris.jniutils

value class JClass internal constructor(val signature: String) {
    override fun toString(): String {
        return signature
    }
}

operator fun Char.plus(clazz: JClass) = this + clazz.signature

val Boolean = JClass("Z")
val Byte = JClass("B")
val Char = JClass("C")
val Short = JClass("S")
val Int = JClass("I")
val Long = JClass("J")
val Float = JClass("F")
val Double = JClass("D")
val Void = JClass("V")
val String = createSignature("java.lang.String")
val Object = createSignature("java.lang.Object")

val BooleanArray = JClass("[Z")
val ByteArray = JClass("[B")
val CharArray = JClass("[C")
val ShortArray = JClass("[S")
val IntArray = JClass("[I")
val LongArray = JClass("[J")
val FloatArray = JClass("[F")
val DoubleArray = JClass("[D")

fun createSignature(fqName: String) = JClass("L${fqName.signature()};")

fun createArraySignature(fqName: String) = JClass('[' + createSignature(fqName))

fun String.signature() = replace('.', '/')
