package ru.profi.skedda.shared.router

import ru.profi.skedda.shared.data.BookingDuration

interface Router {

    val initScreen: Screen

    fun goToSchedule()

    fun goToLogin()

    fun showBooking(id: Long, from: Long, duration: BookingDuration)

    fun goToAccount()
}