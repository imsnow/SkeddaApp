package ru.profi.skedda.shared.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import ru.profi.skedda.shared.network.SkeddaApi

fun initKoin(appDeclaration: KoinAppDeclaration) =
    startKoin {
        appDeclaration()
        modules(commonModules())
    }

fun commonModules() = module {
    single { SkeddaApi() }
}