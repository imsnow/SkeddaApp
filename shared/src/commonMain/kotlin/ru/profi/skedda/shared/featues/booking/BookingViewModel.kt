package ru.profi.skedda.shared.featues.booking

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.profi.skedda.shared.usecases.BookSpaceUseCase
import ru.profi.skedda.shared.usecases.LoadSpaceUseCase

class BookingViewModel internal constructor(
    private val loadSpaceUseCase: LoadSpaceUseCase,
    private val bookSpaceUseCase: BookSpaceUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BookingViewState())
    val state: StateFlow<BookingViewState>
        get() = _state

    fun loadSpace(id: Long) {
        println(">>> book id $id")
        viewModelScope.launch {
            val space = loadSpaceUseCase.loadById(id)
            println(">>> load space $space")
            _state.value = state.value.copy(spaceName = space.name)
        }

    }
}