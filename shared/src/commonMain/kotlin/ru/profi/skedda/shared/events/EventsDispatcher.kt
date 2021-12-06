package ru.profi.skedda.shared.events

interface EventsDispatcher {

    fun dispatchEvent(event: Event)
}