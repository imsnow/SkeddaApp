package ru.profi.skedda.shared.data

data class NearestSchedule(
    val date: String,
    val time: String,
    val space: List<String>
)
