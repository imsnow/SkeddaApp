package ru.profi.skedda.shared.featues.schedule

import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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
        val now = DateTime.now()
        val dateString = now.format(dateFormat)
        val timeString = now.format(timeFormat)
        _state.value = state.value.copy(
            date = dateString,
            time = timeString
        )
        viewModelScope.launch(ceh) {
            val spaces = spaceRepository.loadSpaces(now.unixMillisLong)
            _state.value = state.value.copy(spaces = spaces)
        }
    }

    companion object {
        private val dateFormat = DateFormat("dd MMMM")
        private val timeFormat = DateFormat("hh:mm")
    }
}