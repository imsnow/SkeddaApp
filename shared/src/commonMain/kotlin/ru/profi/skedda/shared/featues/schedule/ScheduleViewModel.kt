package ru.profi.skedda.shared.featues.schedule

import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTimeTz
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.profi.skedda.shared.data.BookingDuration
import ru.profi.skedda.shared.repositories.SpaceRepository
import ru.profi.skedda.shared.router.Router

class ScheduleViewModel(
    private val spaceRepository: SpaceRepository,
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
        val nowLocal = DateTimeTz.nowLocal()
        val dateString = nowLocal.format(dateFormat)
        val timeString = nowLocal.format(timeFormat)
        _state.value = state.value.copy(
            date = dateString,
            time = timeString
        )
        viewModelScope.launch(ceh) {
            val spaces = spaceRepository.loadFreeSpacesFrom(
                fromDateTime = nowLocal.local.unixMillisLong,
                duration = state.value.selectedDuration
            )
            _state.value = state.value.copy(spaces = spaces)
        }
    }

    fun selectDuration(duration: BookingDuration) {
        _state.value = state.value.copy(selectedDuration = duration)
        loadFreeSpaces()
    }

    companion object {
        private val dateFormat = DateFormat("dd MMMM")
        private val timeFormat = DateFormat("hh:mm")
    }
}