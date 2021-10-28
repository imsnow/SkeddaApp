package ru.profi.skedda.android

import android.app.Application
import org.koin.core.component.KoinComponent
import ru.profi.skedda.android.di.appModule
import ru.profi.skedda.shared.di.initKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            modules(appModule)
        }
    }
}