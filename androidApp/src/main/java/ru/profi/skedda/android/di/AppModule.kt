package ru.profi.skedda.android.di

import androidx.compose.material.ExperimentalMaterialApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.profi.skedda.android.ComposeRouter
import ru.profi.skedda.shared.featues.login.LoginViewModel
import ru.profi.skedda.shared.router.Router

@ExperimentalMaterialApi
val appModule = module {
    single <Router> { ComposeRouter() }
}