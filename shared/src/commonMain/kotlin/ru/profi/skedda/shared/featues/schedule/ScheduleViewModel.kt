package ru.profi.skedda.shared.featues.schedule

import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ScheduleViewModel(
//    private val ap
) : ViewModel() {

    private val _state = MutableStateFlow(ScheduleViewState())
    val state: StateFlow<ScheduleViewState>
        get() = _state

    init {
        val now = DateTime.now()
        val dateString = now.format(dateFormat)
        val timeString = now.format(timeFormat)
        _state.value = state.value.copy(
            date = dateString,
            time = timeString
        )
    }

    companion object {
        private val dateFormat = DateFormat("dd MMMM")
        private val timeFormat = DateFormat("hh:mm")
    }
}