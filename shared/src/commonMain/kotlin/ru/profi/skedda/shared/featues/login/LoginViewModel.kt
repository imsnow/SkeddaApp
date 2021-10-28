package ru.profi.skedda.shared.featues.login

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.profi.skedda.shared.network.SkeddaApi

class LoginViewModel(
    private val api: SkeddaApi
) : ViewModel() {

    fun testText() = "Hello Blya"

    private val ceh = CoroutineExceptionHandler { _, throwable ->
        println(">>> exception $throwable")
    }

    init {
        viewModelScope.launch(ceh) {
            api.login(
                email = "miha_mai@mail.ru",
                password = "hui"
            )
            api.webs()
        }
    }
}