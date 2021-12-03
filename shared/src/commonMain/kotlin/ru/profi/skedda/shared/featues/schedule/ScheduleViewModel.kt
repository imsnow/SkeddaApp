package ru.profi.skedda.shared.featues.schedule

import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeTz
import com.soywiz.klock.TimeSpan
import com.soywiz.klock.minutes
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

    private fun DateTime.round(): DateTime {
        val long = this.unixMillisLong
        val min15 = 15.minutes.millisecondsLong
        val min30 = 30.minutes.millisecondsLong
        val d = (long + min15) / min30
        return DateTime(unixMillis = (d * min30).toDouble())
    }

    private fun loadFreeSpaces() {
        val nowLocal = DateTimeTz.nowLocal()
        val round = nowLocal.local.round()
        println(">>>> round ${round.unixMillisLong} not ${nowLocal.local.unixMillisLong}")

        val dateString = round.format(dateFormat)
        val timeString = round.format(timeFormat)
        _state.value = state.value.copy(
            date = dateString,
            time = timeString
        )
        viewModelScope.launch(ceh) {
            val spaces = spaceRepository.loadFreeSpacesFrom(
                fromDateTime = round.unixMillisLong,
                duration = state.value.selectedDuration
            )
            _state.value = state.value.copy(spaces = spaces)
        }
    }

    fun selectDuration(duration: BookingDuration) {
        _state.value = state.value.copy(selectedDuration = duration)
        loadFreeSpaces()
    }

    fun plusTime() {
        val nowLocal = DateTimeTz.nowLocal()
        val round = nowLocal.local.round()
        val newTime = round + 30.minutes

        val dateString = newTime.format(dateFormat)
        val timeString = newTime.format(timeFormat)
        _state.value = state.value.copy(
            date = dateString,
            time = timeString
        )
        viewModelScope.launch(ceh) {
            val spaces = spaceRepository.loadFreeSpacesFrom(
                fromDateTime = newTime.unixMillisLong,
                duration = state.value.selectedDuration
            )
            _state.value = state.value.copy(spaces = spaces)
        }
    }

    companion object {
        private val dateFormat = DateFormat("dd MMMM")
        private val timeFormat = DateFormat("hh:mm")
    }
}