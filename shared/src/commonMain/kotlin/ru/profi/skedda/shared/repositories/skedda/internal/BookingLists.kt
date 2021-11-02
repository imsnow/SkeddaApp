package ru.profi.skedda.shared.repositories.skedda.internal

data class BookingLists(
    val bookings: Array<Booking>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as BookingLists

        if (!bookings.contentEquals(other.bookings)) return false

        return true
    }

    override fun hashCode(): Int {
        return bookings.contentHashCode()
    }
}