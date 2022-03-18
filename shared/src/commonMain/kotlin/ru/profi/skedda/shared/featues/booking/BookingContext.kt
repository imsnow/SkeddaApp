package ru.profi.skedda.shared.featues.booking

import ru.profi.skedda.shared.data.BookingDuration

data class BookingContext(
    val spaceId: Long,
    val from: Long,
    val duration: BookingDuration
)
