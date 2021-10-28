package ru.profi.shared.android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.profi.skedda.shared.featues.login.LoginViewModel

val appModule = module {
    viewModel { LoginViewModel(get()) }
}