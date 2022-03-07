@file:Suppress("unused")

object Versions {
    // Common
    const val kotlinVersion = "1.6.10"
    const val ktorVersion = "1.6.7"
    const val coroutineVersion = "1.6.0-native-mt"
    const val serializationVersion = "1.3.2"
    const val koinVersion = "3.1.5"
    const val klockVersion = "2.4.13"
    const val napierVersion = "2.4.0"

    // Android
    const val composeVersion = "1.1.1"
    const val activityComposeVersion = "1.4.0"
    const val compileSdkVersion = 31
    const val targetSdkVersion = compileSdkVersion
    const val minSdkVersion = 21
}


object Deps {
    // TODO
    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koinVersion}"
        const val android = "io.insert-koin:koin-android:${Versions.koinVersion}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koinVersion}"
    }
}