package ru.profi.skedda.shared.featues.schedule

data class ScheduleViewState(
    val date: String = "",
    val time: String = "",
    val isLoading: Boolean = true,
    val spaces: List<Space> = emptyList()
)

class Space(
    val id: Long,
    val name: String
)