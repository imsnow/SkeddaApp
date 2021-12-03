package ru.profi.skedda.shared.featues.schedule

import ru.profi.skedda.shared.data.BookingDuration
import ru.profi.skedda.shared.repositories.FreeSpace

data class ScheduleViewState(
    val date: String = "",
    val time: String = "",
    val duration: BookingDuration = BookingDuration.ONE_HOUR,
    val isLoading: Boolean = true,
    val spaces: List<FreeSpace> = emptyList()
)