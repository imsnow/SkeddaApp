package ru.profi.skedda.shared.featues.schedule

import com.soywiz.klock.minutes
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.profi.skedda.shared.data.BookingDuration
import ru.profi.skedda.shared.router.Router
import ru.profi.skedda.shared.usecases.LoadFreeSpacesUseCase

class ScheduleViewModel internal constructor(
    private val loadFreeSpacesUseCase: LoadFreeSpacesUseCase,
    private val router: Router
) : ViewModel() {

    private val _state = MutableStateFlow(ScheduleViewState())
    val state: StateFlow<ScheduleViewState>
        get() = _state

    private val ceh = CoroutineExceptionHandler { _, throwable ->
        println(">>> e $throwable")
        router.goToLogin()
    }

    init {
        loadFreeSpaces()
    }

    private fun loadFreeSpaces() {
        viewModelScope.launch(ceh) {
            val currentFromTime = state.value.selectedFrom
            val spaces = loadFreeSpacesUseCase.loadFrom(
                fromDateTime = currentFromTime,
                duration = state.value.selectedDuration
            )
            _state.value = state.value.copy(
                spaces = spaces
            )
        }
    }

    fun selectDuration(duration: BookingDuration) {
        _state.value = state.value.copy(selectedDuration = duration)
        loadFreeSpaces()
    }

    fun onSpaceClicked(id: Long) {
//        viewModelScope.launch {
//
//            val nowLocal = DateTimeTz.nowLocal()
//            val round = nowLocal.local.round()
//
//            val start = round.unixMillisLong
//            val end = start + state.value.selectedDuration.millis
//            spaceRepository.book(id, start, end)
//        }
        router.showBooking(
            id = id,
            from = state.value.selectedFrom,
            duration = state.value.selectedDuration
        )
    }

    fun minusTime() {
        val currentTime = state.value.currentDateTime
        val newTime = currentTime - 30.minutes

        _state.value = state.value.copy(
            currentDateTime = newTime
        )
        loadFreeSpaces()
    }

    fun plusTime() {
        val currentTime = state.value.currentDateTime
        val newTime = currentTime + 30.minutes

        _state.value = state.value.copy(
            currentDateTime = newTime
        )
        loadFreeSpaces()
    }

}