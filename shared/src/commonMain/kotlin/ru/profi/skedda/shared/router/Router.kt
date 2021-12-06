package ru.profi.skedda.shared.router

import ru.profi.skedda.shared.events.EventsDispatcher

interface Router {

    val initScreen: Screen

    fun goToSchedule()

    fun goToLogin()

    fun showBooking(id: Long)
}