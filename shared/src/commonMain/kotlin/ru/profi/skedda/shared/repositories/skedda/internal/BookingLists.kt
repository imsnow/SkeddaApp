package ru.profi.skedda.shared.repositories.skedda.internal

import kotlinx.serialization.Serializable

@Serializable
data class BookingLists(
    val bookings: List<Booking>
)