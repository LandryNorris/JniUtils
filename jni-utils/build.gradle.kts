plugins {
    kotlin("multiplatform")
}

version = "0.1.0-SNAPSHOT"

kotlin {
    val androidTargets = listOf(androidNativeArm64(), androidNativeArm32(),
        androidNativeX64(), androidNativeX86())

    androidTargets.forEach {
        it.binaries {
            sharedLib("jni_utils")
        }
    }
    
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
