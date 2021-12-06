package ru.profi.skedda.shared.featues.main

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.profi.skedda.shared.events.Event
import ru.profi.skedda.shared.events.EventHandler
import ru.profi.skedda.shared.events.MainEvent
import ru.profi.skedda.shared.featues.login.LoginViewState
import ru.profi.skedda.shared.repositories.UserRepository

class MainViewModel internal constructor(
    userRepository: UserRepository
) : ViewModel(), EventHandler {

    private val _state = MutableStateFlow(MainViewState())
    val state: StateFlow<MainViewState>
        get() = _state

    init {
        viewModelScope.launch {
            val hasUser = userRepository.loadUser()
            val type = if (hasUser) LoginType.HAS_USER else LoginType.NEED_LOGIN
            _state.value = _state.value.copy(type = type)

            delay(3000L)
            _state.value = _state.value.copy(bottomSheetExpanded = true)

            delay(3000L)
            _state.value = _state.value.copy(bottomSheetExpanded = false)
        }
    }

    override fun handleEvent(event: Event) {
        println(">>> handle event $event")
    }
}