package ru.profi.skedda.shared.featues.schedule

import ru.profi.skedda.shared.repositories.FreeSpace

data class ScheduleViewState(
    val date: String = "",
    val time: String = "",
    val isLoading: Boolean = true,
    val spaces: List<FreeSpace> = emptyList()
)