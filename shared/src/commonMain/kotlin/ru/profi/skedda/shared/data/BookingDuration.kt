package ru.profi.skedda.shared.data

enum class BookingDuration(val millis: Long) {
    HALF_HOUR(30 * 60 * 1000L),
    ONE_HOUR(HALF_HOUR.millis * 2)
}