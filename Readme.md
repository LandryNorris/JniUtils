JNI Utils
========

using this library:

```kotlin
implementation("io.github.landrynorris:jni-utils:0.0.1-alpha01")
```

This library provides bindings to the JNI library on Android.

The primary motivator of this library is the syntax for calling
a method on JNIEnvVar

```kotlin
env.pointed.pointed?.SetObjectField?.invoke(env, fieldId, value)
```

This library allows you to write

```kotlin
env.setObjectField(fieldId, value)
```

Or take for example, converting a Kotlin String to a jstring:

```kotlin
//Raw JNI library
memScoped {
    env.pointed.pointed?.newString?.invoke(myString.wcstr.ptr, length)
}

//This library
myString.toJString(env)
```

Registering natives
-------------------

This library provides a DSL for registering JNI methods.

```kotlin
env.registerNatives {
    clazz = env.findClass("io.github.landrynorris.sample.JniBridge".signature())

    method("buttonClicked") {
        signature = signature(::buttonClicked)
        function = staticCFunction(::buttonClicked)
    }

    method("getText") {
        signature = Signature(listOf(Long), String).toString()
        function = staticCFunction(::getText)
    }
}
```

You must provide a clazz value in the DSL.

There are two ways of defining the signature:

1. the Signature() constructor. Provide a list of parameter 
signatures and a single return signature. The Signature#toString()
method creates a signature that the JNI can recognize.
2. For simple methods (jstring, jobject, etc. are not currently 
supported), you can use the signature(Function) method to
automatically create a signature.
