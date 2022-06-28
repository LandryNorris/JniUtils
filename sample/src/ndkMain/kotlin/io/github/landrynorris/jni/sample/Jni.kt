package io.github.landrynorris.jni.sample

import io.github.landrynorris.jniutils.*
import kotlinx.cinterop.*
import kotlinx.cinterop.nativeHeap.alloc
import platform.android.JNIEnvVar
import platform.android.jobject
import platform.android.jstring
import platform.android.jvalue

@CName("Java_io_github_landrynorris_sample_JniBridge_methodWithParameters")
fun methodWithParameters(env: CPointer<JNIEnvVar>, thiz: jobject, intValue: Int) {
    println("Got int from JVM. Value is $intValue")
}

@CName("Java_io_github_landrynorris_sample_JniBridge_callJavaFunction")
fun callJavaFunction(env: CPointer<JNIEnvVar>, thiz: jobject, value: Double) {
    cValue<jvalue> {  }.useContents { this }
    val clazz = env.findClass("io/github/landrynorris/sample/JavaClass")
        ?: error("Unable to find class")
    val constructorId = env.getMethodId(clazz, "<init>", "()V")
        ?: error("Unable to find constructor")
    val obj = env.newObject(clazz, constructorId)
    val methodId = env.getMethodId(clazz, "square", "(D)D")
        ?: error("Unable to find method")
    val square = env.callDoubleMethod(obj, methodId, value.jvalue)
    println("The value of $value squared is $square")
}

@CName("Java_io_github_landrynorris_sample_JniBridge_createRepository")
fun createRepository(env: CPointer<JNIEnvVar>, thiz: jobject): Long {
    val repository = ButtonClickRepository()
    println("Converting repository to pointer")
    return StableRef.create(repository).asCPointer().toLong()
}

@CName("Java_io_github_landrynorris_sample_JniBridge_buttonClicked")
fun buttonClicked(env: CPointer<JNIEnvVar>, thiz: jobject, ptr: Long) {
    println("Getting repository from pointer")
    val repository = ptr.pointed<ButtonClickRepository>() ?: error("ptr must not be null")
    println("Found repository for clicked")
    repository.clicked()
}

@CName("Java_io_github_landrynorris_sample_JniBridge_getText")
fun getText(env: CPointer<JNIEnvVar>, thiz: jobject, ptr: Long): jstring {
    val repository = ptr.pointed<ButtonClickRepository>() ?: error("ptr must not be null")
    println("Found repository for getting text")
    println("Repository text is ${repository.getCounter()}")
    return repository.getText().toJString(env) ?: error("Unable to create JString")
}
