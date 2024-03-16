
plugins {
    id("com.android.application")
    kotlin("multiplatform")
}

kotlin {
    androidTarget()
    val androidNdkTargets = listOf(androidNativeArm64(), androidNativeArm32(),
        androidNativeX64(), androidNativeX86())

    androidNdkTargets.forEach {
        it.binaries {
            sharedLib()
        }
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        androidNativeMain.get().dependencies {
            implementation(project(":jni-utils"))
            //implementation("io.github.landrynorris:jni-utils:$version")
        }

        androidMain.get().dependencies {
            implementation("androidx.appcompat:appcompat:1.6.0")
        }

        val androidUnitTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
                implementation("androidx.test.ext:junit:1.1.5")
                implementation("androidx.test.espresso:espresso-core:3.5.1")
            }
        }
    }
}

android {
    namespace = "io.github.landrynorris.sample"
    compileSdk = 34
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = false
    }
}

repositories {
    mavenCentral()
    google()
    mavenLocal()
}

tasks {
    val prepareAndroidNdkSo by creating {
        dependsOn(build)
        val debugArm32SoFolder = File(buildDir, "bin/androidNativeArm32/debugShared")
        val jniArm32Folder = File(projectDir, "src/androidMain/jniLibs/armeabi-v7a")
        val debugArm64SoFolder = File(buildDir, "bin/androidNativeArm64/debugShared")
        val jniArm64Folder = File(projectDir, "src/androidMain/jniLibs/arm64-v8a")

        val debugX86SoFolder = File(buildDir, "bin/androidNativeX86/debugShared")
        val jniX86Folder = File(projectDir, "src/androidMain/jniLibs/x86")
        val debugX64SoFolder = File(buildDir, "bin/androidNativeX64/debugShared")
        val jniX64Folder = File(projectDir, "src/androidMain/jniLibs/x86_64")

        doLast {
            copy {
                from(debugArm32SoFolder)
                into(jniArm32Folder)
                include("*.so")
            }
            copy {
                from(debugArm64SoFolder)
                into(jniArm64Folder)
                include("*.so")
            }
            copy {
                from(debugX86SoFolder)
                into(jniX86Folder)
                include("*.so")
            }
            copy {
                from(debugX64SoFolder)
                into(jniX64Folder)
                include("*.so")
            }
        }
    }
}
