package ru.profi.skedda.shared.events

interface EventHandler {

    fun handleEvent(event: Event)
}