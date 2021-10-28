package ru.profi.shared.android

import android.app.Application
import ru.profi.shared.android.di.appModule
import ru.profi.skedda.shared.di.initKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            appModule
        }
    }
}