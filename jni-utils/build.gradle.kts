import java.util.Properties

plugins {
    kotlin("multiplatform")
    id("maven-publish")
    id("org.jetbrains.dokka") version "1.6.20"
    id("signing")
}

group = "io.github.landrynorris"
version = "0.1.0-rc01"

val properties by lazy {
    Properties().also { it.load(project.rootProject.file("local.properties").inputStream()) }
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

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

publishing {
    publications {
        withType<MavenPublication> {
            artifact(javadocJar.get())
            pom {
                name.set("jni-utils")
                description.set("Kotlin/Native bindings for JNI on android ndk")
                url.set("https://github.com/LandryNorris/JniUtils")
                licenses {
                    license {
                        name.set("The MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                scm {
                    connection.set("https://github.com/LandryNorris/JniUtils.git")
                    developerConnection.set("https://github.com/LandryNorris/JniUtils")
                    url.set("https://github.com/LandryNorris/JniUtils")
                }
                developers {
                    developer {
                        id.set("landrynorris")
                        name.set("Landry Norris")
                        email.set("landry.norris0@gmail.com")
                    }
                }
            }
        }
    }

    repositories {
        maven {
            name = "sonatype"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")

            credentials {
                username = getProperty("sonatype.username")
                password = getProperty("sonatype.password")
            }
        }
    }
}

project.signing {
    val secretKeyFile = getProperty("signing.secretKeyRingFile") ?: error("No key file found")
    val secretKey = File(secretKeyFile).readText()
    val signingPassword = getProperty("signing.password")
    useInMemoryPgpKeys(secretKey, signingPassword)
    sign(project.publishing.publications)
}

fun getProperty(name: String): String? {
    return System.getProperty(name) ?: properties.getProperty(name)
}
