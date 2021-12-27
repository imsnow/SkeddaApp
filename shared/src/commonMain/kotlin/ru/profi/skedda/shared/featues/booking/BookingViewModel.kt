package ru.profi.skedda.shared.featues.booking

import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.profi.skedda.shared.router.Router
import ru.profi.skedda.shared.usecases.BookSpaceUseCase
import ru.profi.skedda.shared.usecases.LoadSpaceUseCase

class BookingViewModel internal constructor(
    private val loadSpaceUseCase: LoadSpaceUseCase,
    private val bookSpaceUseCase: BookSpaceUseCase,
    private val router: Router
) : ViewModel() {

    private val _state = MutableStateFlow(BookingViewState())
    val state: StateFlow<BookingViewState>
        get() = _state

    fun loadSpace(context: BookingContext) {
        println(">>> book id ${context.spaceId}")
        viewModelScope.launch {
            val space = loadSpaceUseCase.loadById(context.spaceId)
            val from = DateTime.fromUnix(context.from)
            val fromFormatted = from.format(timeFormat)
            val end = DateTime.fromUnix(context.from + context.duration.millis)
            val endFormatted = end.format(timeFormat)
            println(">>> load space $space")
            _state.value = state.value.copy(
                spaceName = space.name,
                fromTo = "$fromFormatted - $endFormatted",
                isSpaceLoaded = true
            )
        }
    }

    fun goBook() {
        println(">>> go book")
    }

    fun cancel() {
        router.goToSchedule()
    }

    companion object {
        private val timeFormat = DateFormat("hh:mm")
    }
}