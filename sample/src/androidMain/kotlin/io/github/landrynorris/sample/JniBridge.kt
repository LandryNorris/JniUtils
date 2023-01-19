package io.github.landrynorris.sample

import io.github.landrynorris.jni.sample.SharedClass

object JniBridge {
    init {
        println("Loading library for JNI")
        System.loadLibrary("sample")
        println("Loaded library")
    }

    external fun methodWithParameters(value: Int)
    external fun handleShared(sharedClass: SharedClass): String
    external fun doubleAll(array: DoubleArray): DoubleArray
    external fun callJavaFunction(value: Double)
    external fun createDataClass(s: String, i: Int, d: Double, doubles: DoubleArray): Any
    external fun buttonClicked(ptr: Long)
    external fun getText(ptr: Long): String
    external fun createRepository(): Long
    external fun crash(message: String)
}

class JavaClass {
    fun printIntValue(value: Int) {
        println("Value is $value")
    }

    fun square(value: Double): Double {
        println("Value to square is $value")
        return value*value
    }
}
