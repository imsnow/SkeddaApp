package ru.profi.skedda.shared.data.internal

import kotlinx.serialization.Serializable

@Serializable
data class BookingLists(
    val bookings: List<Booking>
)