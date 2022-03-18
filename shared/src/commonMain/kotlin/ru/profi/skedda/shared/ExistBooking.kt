package ru.profi.skedda.shared

import com.soywiz.klock.Time

data class ExistBooking(
    val id: Long,
    val startTime: Time,
    val endTime: Time
)
