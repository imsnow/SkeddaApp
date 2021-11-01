package ru.profi.skedda.shared.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import ru.profi.skedda.shared.featues.login.LoginViewModel
import ru.profi.skedda.shared.network.SkeddaApi
import ru.profi.skedda.shared.validators.EmailValidator
import ru.profi.skedda.shared.validators.PasswordValidator

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModules(), viewModules())
    }

// for iOS
fun initKoin() = initKoin {}

fun commonModules() = module {
    single { SkeddaApi() }
    single { EmailValidator }
    single { PasswordValidator }
}

fun viewModules() = module {
    factory { LoginViewModel(get(), get(), get()) }
}