@file:Suppress("unused")

object Versions {
    // Common
    const val kotlinVersion = "1.5.31"
    const val ktorVersion = "1.6.4"
    const val coroutineVersion = "1.5.2-native-mt"
    const val serializationVersion = "1.3.0"
    const val koinVersion = "3.1.3"
    const val klockVersion = "2.0.7"

    // Android
    const val composeVersion = "1.0.4"
    const val activityComposeVersion = "1.4.0"
    const val compileSdkVersion = 31
    const val targetSdkVersion = compileSdkVersion
    const val minSdkVersion = 21
}


object Deps {
    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koinVersion}"
        const val android = "io.insert-koin:koin-android:${Versions.koinVersion}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koinVersion}"
    }
}