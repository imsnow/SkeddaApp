package ru.profi.skedda.shared.featues.booking

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.profi.skedda.shared.featues.login.LoginViewState

class BookingViewModel() : ViewModel() {

    private val _state = MutableStateFlow(BookingViewState())
    val state: StateFlow<BookingViewState>
        get() = _state
}