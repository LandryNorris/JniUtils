package io.github.landrynorris.jni.sample

class SharedClass(val x: Double, val dataHolder: DataHolder)

data class DataHolder(val i: Int, val d: Double, val s: String)
