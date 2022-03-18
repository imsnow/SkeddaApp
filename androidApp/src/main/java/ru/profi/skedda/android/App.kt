package ru.profi.skedda.android

import android.app.Application
import androidx.compose.material.ExperimentalMaterialApi
import ru.profi.skedda.android.di.appModule
import ru.profi.skedda.shared.di.initKoin

@ExperimentalMaterialApi
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            modules(appModule)
        }
    }
}
