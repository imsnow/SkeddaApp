package ru.profi.skedda.shared.featues.main

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.profi.skedda.shared.usecases.CheckHasUserUseCase
import ru.profi.skedda.shared.usecases.LoginUseCase

class MainViewModel internal constructor(
    checkHasUserUseCase: CheckHasUserUseCase,
    loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainViewState())
    val state: StateFlow<MainViewState>
        get() = _state

    init {
        println(">>> init MainViewModel $this")
        viewModelScope.launch {
            val credential = checkHasUserUseCase.checkUser()
            val type = if (credential != null) {
                loginUseCase.login(email = credential.email, password = credential.password)
                LoginType.HAS_USER
            } else LoginType.NEED_LOGIN
            _state.value = state.value.copy(type = type)
        }
    }
}