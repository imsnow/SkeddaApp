package ru.profi.skedda.shared.events

sealed class MainEvent : Event {

    object ShowBooking : MainEvent()
}
