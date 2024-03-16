package io.github.landrynorris.jni.sample

import io.github.landrynorris.jniutils.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import platform.android.JNIEnvVar
import platform.android.jobject

@OptIn(ExperimentalForeignApi::class)
fun jobject.toSharedClass(env: CPointer<JNIEnvVar>): SharedClass? {
    val clazz = env.findClass("io/github/landrynorris/jni/sample/SharedClass") ?: error("Unable to find class")
    if(!env.isInstanceOf(this, clazz)) return null
    val x = env.getDoubleField(this, "x", clazz)
    val holder = env.getObjectField(this, "dataHolder", clazz, signature<DataHolder>().toString())
    return SharedClass(x, holder?.toDataHolder(env)!!)
}

@OptIn(ExperimentalForeignApi::class)
fun jobject.toDataHolder(env: CPointer<JNIEnvVar>): DataHolder? {
    val clazz = env.findClass("io/github/landrynorris/jni/sample/DataHolder") ?: error("Unable to find class")
    if(!env.isInstanceOf(this, clazz)) return null
    val i = env.getIntField(this, "i", clazz)
    val d = env.getDoubleField(this, "d", clazz)
    val s = env.getStringField(this, "s", clazz)

    return DataHolder(i, d, s)
}
