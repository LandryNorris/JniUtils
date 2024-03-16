package io.github.landrynorris.jni.sample

import io.github.landrynorris.jniutils.*
import kotlinx.cinterop.*
import platform.android.*
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalForeignApi::class)
fun methodWithParameters(env: CPointer<JNIEnvVar>, thiz: jobject, intValue: Int) {
    println("Got int from JVM. Value is $intValue")
}

@OptIn(ExperimentalForeignApi::class)
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

@OptIn(ExperimentalForeignApi::class)
fun createDataClass(env: CPointer<JNIEnvVar>, thiz: jobject, s: jstring, i: Int,
                    d: Double, doubles: jdoubleArray): jobject? {
    // We can use ::class because the class is shared between JNI and JVM source set.
    // This is helpful for reducing mistakes
    val clazz = env.findClass(DataClass::class.qualifiedName?.signature() ?: "")
        ?: error("Can't find class")
    val constructorSignature = Signature(listOf(String, Int, Double, DoubleArray), Void)
    val constructorId = env.getMethodId(clazz, "<init>", constructorSignature.toString())
    return memScoped {
        env.newObject(clazz, constructorId!!, s.jvalue(this),
            i.jvalue(this), d.jvalue(this), doubles.jvalue(this))
    }
}

@OptIn(ExperimentalForeignApi::class)
fun doubleAll(env: CPointer<JNIEnvVar>, thiz: jobject, array: jdoubleArray): jdoubleArray {
    val doubleArray = env.getDoubleArrayElements(array)
    return doubleArray.map { it*2 }.toDoubleArray().toJava(env)
}

@OptIn(ExperimentalForeignApi::class)
fun createRepository(env: CPointer<JNIEnvVar>, thiz: jobject): Long {
    val repository = ButtonClickRepository()
    println("Converting repository to pointer")
    return StableRef.create(repository).asCPointer().toLong()
}

@OptIn(ExperimentalForeignApi::class)
fun buttonClicked(env: CPointer<JNIEnvVar>, thiz: jobject, ptr: Long) {
    println("Getting repository from pointer")
    val repository = ptr.pointed<ButtonClickRepository>() ?: error("ptr must not be null")
    println("Found repository for clicked")
    repository.clicked()
}

@OptIn(ExperimentalForeignApi::class)
fun getText(env: CPointer<JNIEnvVar>, thiz: jobject, ptr: Long): jstring {
    val repository = ptr.pointed<ButtonClickRepository>() ?: error("ptr must not be null")
    println("Found repository for getting text")
    println("Repository text is ${repository.getCounter()}")
    return repository.getText().toJString(env) ?: error("Unable to create JString")
}

@OptIn(ExperimentalForeignApi::class)
fun handleSharedClass(env: CPointer<JNIEnvVar>, thiz: jobject, data: jobject): jstring {
    val shared = data.toSharedClass(env)!!
    val total = shared.x * shared.dataHolder.i * shared.dataHolder.d
    val s = shared.dataHolder.s + ": " + total
    return s.toJString(env) ?: error("Unable to create JString")
}

@OptIn(ExperimentalForeignApi::class)
fun signatureWithCType(env: CPointer<JNIEnvVar>, thiz: jobject, s: jstring): jboolean {
    return try {
        println("Signature is ${signature(::signatureWithCType)}")
        false.toJBoolean()
    } catch (e: IllegalStateException) {
        true.toJBoolean()
    }
}

@OptIn(ExperimentalForeignApi::class)
fun crash(env: CPointer<JNIEnvVar>, thiz: jobject, message: jstring) {
    val messageString = env.getString(message)
    env.fatalError(messageString)
}

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("JNI_OnLoad")
fun loadJni(jvm: CPointer<JavaVMVar>, reserved: CPointer<*>): Int {
    val env = jvm.env() ?: error("Unable to get JNI environment")
    registerJniNatives(env)

    return JNI_VERSION_1_6
}

@OptIn(ExperimentalForeignApi::class)
fun registerJniNatives(env: CPointer<JNIEnvVar>) {
    env.registerNatives {
        clazz = env.findClass("io.github.landrynorris.sample.JniBridge".signature())

        method("buttonClicked") {
            signature = signature(::buttonClicked)
            function = staticCFunction(::buttonClicked)
        }

        method("createRepository") {
            signature = signature(::createRepository)
            function = staticCFunction(::createRepository)
        }

        method("getText") {
            signature = Signature(listOf(Long), String).toString()
            function = staticCFunction(::getText)
        }

        method("methodWithParameters") {
            signature = signature(::methodWithParameters)
            function = staticCFunction(::methodWithParameters)
        }

        method("callJavaFunction") {
            signature = signature(::callJavaFunction)
            function = staticCFunction(::callJavaFunction)
        }

        method("createDataClass") {
            signature = Signature(listOf(String, Int, Double, DoubleArray), Object).toString()
            function = staticCFunction(::createDataClass)
        }

        method("crash") {
            signature = Signature(listOf(String)).toString()
            function = staticCFunction(::crash)
        }

        method("doubleAll") {
            signature = Signature(listOf(DoubleArray), DoubleArray).toString()
            function = staticCFunction(::doubleAll)
        }

        method("handleShared") {
            signature = Signature(listOf(signature<SharedClass>()), String).toString()
            function = staticCFunction(::handleSharedClass)
        }

        method("signatureWithCType") {
            signature = Signature(listOf(String), Boolean).toString()
            function = staticCFunction(::signatureWithCType)
        }
    }
    println("Finished registering native methods")
}
