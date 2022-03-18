buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}")
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlint}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        disabledRules.set(setOf("no-wildcard-imports"))
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}