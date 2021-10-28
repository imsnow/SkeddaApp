plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:${Versions.composeVersion}")
    implementation("androidx.compose.ui:ui-tooling:${Versions.composeVersion}")
    implementation("androidx.compose.foundation:foundation:${Versions.composeVersion}")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.material:material:${Versions.composeVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineVersion}")
}

android {
    compileSdk = Versions.compileSdkVersion
    defaultConfig {
        applicationId = "ru.profi.skedda.android"
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdkVersion
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures.compose = true
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}