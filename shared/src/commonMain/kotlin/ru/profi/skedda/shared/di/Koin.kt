package ru.profi.skedda.shared.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.profi.skedda.shared.events.Event
import ru.profi.skedda.shared.events.EventHandler
import ru.profi.skedda.shared.events.EventsDispatcher
import ru.profi.skedda.shared.events.MainEvent
import ru.profi.skedda.shared.featues.login.LoginViewModel
import ru.profi.skedda.shared.featues.main.MainViewModel
import ru.profi.skedda.shared.featues.schedule.ScheduleViewModel
import ru.profi.skedda.shared.network.NetworkClient
import ru.profi.skedda.shared.network.SkeddaApi
import ru.profi.skedda.shared.repositories.SpaceRepository
import ru.profi.skedda.shared.repositories.UserRepository
import ru.profi.skedda.shared.validators.EmailValidator
import ru.profi.skedda.shared.validators.PasswordValidator

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModules(), networkModule(), viewModules())
    }

// for iOS
fun initKoin() = initKoin {}

fun commonModules() = module {
    single { EmailValidator }
    single { PasswordValidator }
    single { UserRepository(get()) }
    single { SpaceRepository(get()) }
}

fun networkModule() = module {
    single { NetworkClient() }
    single { SkeddaApi(get()) }
}

fun viewModules() = module {
    factory { LoginViewModel(get(), get(), get(), get()) }
    factory { ScheduleViewModel(get(), get()) }
    factory { MainViewModel(get()) } bind EventHandler::class
}

class HH(private val eventHandler: EventHandler): EventsDispatcher {


    override fun dispatchEvent(event: Event) {
        eventHandler.handleEvent(MainEvent.ShowBooking)
    }
}