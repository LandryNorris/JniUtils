import org.gradle.configurationcache.extensions.capitalized

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

enum class Platform(val platformName: String, val archName: String) {
    AndroidArm64("androidNativeArm64", "arm64-v8a"),
    AndroidArm32("androidNativeArm32", "armeabi-v7a"),
    AndroidX64("androidNativeX64", "x86_64"),
    AndroidX86("androidNativeX86", "x86")
}

/**
 * Create a task to prepare binary for a given platform.
 */
fun createSoPrepareTask(platform: Platform, flavor: String = "debug"): Task {
    return tasks.create("prepare${platform.platformName}So") {
        dependsOn("link${flavor.capitalized()}Shared${platform.platformName.capitalized()}")
        val srcFolder = layout.buildDirectory.file("bin/${platform.platformName}/${flavor}Shared")
        val destFolder = projectDir.resolve("src/androidMain/jniLibs/${platform.archName}")

        doLast {
            copy {
                from(srcFolder)
                into(destFolder)
                include("*.so")
            }
        }
    }
}

val binaryTasks = listOf(
    createSoPrepareTask(Platform.AndroidArm64),
    createSoPrepareTask(Platform.AndroidArm32),
    createSoPrepareTask(Platform.AndroidX64),
    createSoPrepareTask(Platform.AndroidX86)
)

tasks.create("prepareAndroidNdkSo") {
    binaryTasks.forEach {
        this.dependsOn(it)
    }
}
