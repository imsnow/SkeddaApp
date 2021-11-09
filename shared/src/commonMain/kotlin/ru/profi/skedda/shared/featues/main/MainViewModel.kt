package ru.profi.skedda.shared.featues.main

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.profi.skedda.shared.featues.login.LoginViewState
import ru.profi.skedda.shared.repositories.UserRepository

class MainViewModel internal constructor(
    userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MainViewState())
    val state: StateFlow<MainViewState>
        get() = _state

    init {
        val hasUser = userRepository.loadUser() != null
        _state.value = _state.value.copy(needLogin = !hasUser)
    }
}