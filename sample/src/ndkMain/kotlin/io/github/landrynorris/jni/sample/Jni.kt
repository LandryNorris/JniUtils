package io.github.landrynorris.jni.sample

import io.github.landrynorris.jniutils.*
import kotlinx.cinterop.*
import kotlinx.cinterop.nativeHeap.alloc
import platform.android.*
import kotlin.reflect.KClass

fun methodWithParameters(env: CPointer<JNIEnvVar>, thiz: jobject, intValue: Int) {
    println("Got int from JVM. Value is $intValue")
}

fun callJavaFunction(env: CPointer<JNIEnvVar>, thiz: jobject, value: Double) {
    val clazz = env.findClass("io/github/landrynorris/sample/JavaClass")
        ?: error("Unable to find class")
    val constructorId = env.getMethodId(clazz, "<init>", "()V")
        ?: error("Unable to find constructor")
    val obj = env.newObject(clazz, constructorId)!!
    val methodId = env.getMethodId(clazz, "square", "(D)D")
        ?: error("Unable to find method")
    memScoped {
        val square = env.callDoubleMethod(obj, methodId, value.jvalue(this))
        println("The value of $value squared is $square")
    }
}

fun createRepository(env: CPointer<JNIEnvVar>, thiz: jobject): Long {
    val repository = ButtonClickRepository()
    println("Converting repository to pointer")
    return StableRef.create(repository).asCPointer().toLong()
}

fun buttonClicked(env: CPointer<JNIEnvVar>, thiz: jobject, ptr: Long) {
    println("Getting repository from pointer")
    val repository = ptr.pointed<ButtonClickRepository>() ?: error("ptr must not be null")
    println("Found repository for clicked")
    repository.clicked()
}

fun getText(env: CPointer<JNIEnvVar>, thiz: jobject, ptr: Long): jstring {
    val repository = ptr.pointed<ButtonClickRepository>() ?: error("ptr must not be null")
    println("Found repository for getting text")
    println("Repository text is ${repository.getCounter()}")
    return repository.getText().toJString(env) ?: error("Unable to create JString")
}

@CName("JNI_OnLoad")
fun loadJni(jvm: CPointer<JavaVMVar>, reserved: CPointer<*>): Int {
    val env = jvm.env() ?: error("Unable to get JNI environment")
    registerJniNatives(env)

    val types = listOf(JNIMethod::class, kotlin.Int::class, kotlin.Double::class)
    println("Types are: ${types.joinToString(", ") { classToSignature(it).signature }}")
    return JNI_VERSION_1_6
}

fun registerJniNatives(env: CPointer<JNIEnvVar>) {
    env.registerNatives {
        clazz = env.findClass("io.github.landrynorris.sample.JniBridge".signature())

        method {
            name = "buttonClicked"
            signature = Signature(listOf(Long), Void).toString()
            function = staticCFunction(::buttonClicked)
        }

        method {
            name = "createRepository"
            signature = Signature(listOf(), Long).toString()
            function = staticCFunction(::createRepository)
        }

        method {
            name = "getText"
            signature = Signature(listOf(Long), String).toString()
            function = staticCFunction(::getText)
        }

        method {
            name = "methodWithParameters"
            signature = Signature(listOf(Int), Void).toString()
            function = staticCFunction(::methodWithParameters)
        }

        method {
            name = "callJavaFunction"
            signature = Signature(listOf(Double), Void).toString()
            function = staticCFunction(::callJavaFunction)
        }
    }
    println("Finished registering native methods")
}
