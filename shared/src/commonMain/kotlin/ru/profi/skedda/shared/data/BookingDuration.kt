package ru.profi.skedda.shared.data

enum class BookingDuration(val title: String, val millis: Long) {
    HALF_HOUR(title = "Пол часа", 30 * 60 * 1000L),
    ONE_HOUR(title = "Часик",HALF_HOUR.millis * 2),
    HOUR_AND_HALF(title = "Полтора",HALF_HOUR.millis * 3),
    TWO_HOURS(title = "2 час",ONE_HOUR.millis * 2);

    companion object {
        fun String.fromMillis() = values().first { it.name == this }
    }
}