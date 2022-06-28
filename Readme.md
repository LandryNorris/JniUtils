JNI Utils
========

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


