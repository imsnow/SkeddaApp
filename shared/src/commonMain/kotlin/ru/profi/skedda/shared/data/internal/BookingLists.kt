package ru.profi.skedda.shared.data.internal

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class BookingLists(
    val bookings: List<Booking>,
    val bookingslist: BookingsList
)

@Serializable
data class BookingsList(
    val idx: JsonObject
)
