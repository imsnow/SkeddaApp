package ru.profi.skedda.shared.di

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.koin.core.context.startKoin
import org.koin.core.definition.Definition
import org.koin.core.instance.InstanceFactory
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import ru.profi.skedda.shared.Storage
import ru.profi.skedda.shared.featues.booking.BookingViewModel
import ru.profi.skedda.shared.featues.login.LoginViewModel
import ru.profi.skedda.shared.featues.main.MainViewModel
import ru.profi.skedda.shared.featues.schedule.ScheduleViewModel
import ru.profi.skedda.shared.network.NetworkClient
import ru.profi.skedda.shared.network.SkeddaApi
import ru.profi.skedda.shared.usecases.BookSpaceUseCase
import ru.profi.skedda.shared.usecases.CheckHasUserUseCase
import ru.profi.skedda.shared.usecases.LoadFreeSpacesUseCase
import ru.profi.skedda.shared.usecases.LoadSpaceUseCase
import ru.profi.skedda.shared.usecases.LoginUseCase
import ru.profi.skedda.shared.validators.EmailValidator
import ru.profi.skedda.shared.validators.PasswordValidator

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModules(), networkModule(), useCases(), viewModules())
    }

// for iOS
fun initKoin() = initKoin {}

fun commonModules() = module {
    single { EmailValidator }
    single { PasswordValidator }
    single { Storage() }
}

fun networkModule() = module {
    single { NetworkClient() }
    single { SkeddaApi(get()) }
}

fun viewModules() = module {
    viewModel { LoginViewModel(get(), get(), get(), get()) }
    viewModel { ScheduleViewModel(get(), get()) }
    viewModel { MainViewModel(get(), get()) }
    viewModel { BookingViewModel(get(), get() ,get()) }
}

fun useCases() = module {
    single { CheckHasUserUseCase(get(), get()) }
    single { LoginUseCase(get(), get()) }
    single { LoadFreeSpacesUseCase(get(), get()) }
    single { LoadSpaceUseCase(get()) }
    single { BookSpaceUseCase(get(), get()) }
}

inline fun <reified T : ViewModel> Module.viewModel(
    qualifier: Qualifier? = null,
    noinline definition: Definition<T>
): Pair<Module, InstanceFactory<T>> {
    return factory(qualifier, definition)
}