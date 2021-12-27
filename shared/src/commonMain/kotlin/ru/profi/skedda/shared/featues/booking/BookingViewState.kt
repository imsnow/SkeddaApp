package ru.profi.skedda.shared.featues.booking

data class BookingViewState(
    val spaceName: String = "",
    val fromTo: String = "",
    val isSpaceLoaded: Boolean = false
)