package ru.profi.skedda.shared.featues.schedule

import ru.profi.skedda.shared.data.BookingDuration
import ru.profi.skedda.shared.repositories.FreeSpace

data class ScheduleViewState(
    val date: String = "",
    val time: String = "",
    val durations: List<BookingDuration> = BookingDuration.values().toList(),
    val selectedDuration: BookingDuration = BookingDuration.ONE_HOUR,
    val isLoading: Boolean = true,
    val spaces: List<FreeSpace> = emptyList()
)