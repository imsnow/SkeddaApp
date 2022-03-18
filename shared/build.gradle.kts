import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version (Versions.kotlinVersion)
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("dev.icerock.moko:mvvm-core:0.11.0")
                implementation("com.russhwolf:multiplatform-settings-no-arg:0.8.1")
                implementation("io.ktor:ktor-client-core:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-serialization:${Versions.ktorVersion}")
                implementation("io.ktor:ktor-client-logging:${Versions.ktorVersion}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineVersion}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serializationVersion}")
                implementation("com.soywiz.korlibs.klock:klock:${Versions.klockVersion}")
                implementation(Deps.Koin.core)
                implementation("io.github.aakira:napier:${Versions.napierVersion}")
            }
        }
//        val commonTest by getting {
//            dependencies {
//                implementation(kotlin("test-common"))
//                implementation(kotlin("test-annotations-common"))
//            }
//        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:${Versions.ktorVersion}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineVersion}")
            }
        }
//        val androidTest by getting {
//            dependencies {
//                implementation(kotlin("test-junit"))
//                implementation("junit:junit:4.13.2")
//            }
//        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:${Versions.ktorVersion}")
            }
        }
//        val iosTest by getting
    }
}

android {
    compileSdk = Versions.compileSdkVersion
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdkVersion
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
