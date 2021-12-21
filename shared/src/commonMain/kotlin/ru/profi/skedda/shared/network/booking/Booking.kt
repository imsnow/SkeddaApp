package ru.profi.skedda.shared.network.booking

import kotlinx.serialization.Serializable
import ru.profi.skedda.shared.data.internal.Venue

@Serializable
class Booking(
    val venue: Venue,
    val spaces: List<Long>,
    val title: String = "Тайтл",
    val start: String,
    val end: String,
    val endOfLastOccurrence: String? = null,
    val syncToExternalCalendar: Boolean = true,
    val customFields: List<CustomFields> = listOf(noteCustomField),
    val type: Int = 1,
    val price: Int = 0
)

@Serializable
class BookingPayload(
    val booking: Booking
)

@Serializable
class CustomFields(
    val id: String,
    val value: String?
)

val noteCustomField = CustomFields(id = "notes", value = "test notes")