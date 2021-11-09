package ru.profi.skedda.shared.router

interface Router {

    val initScreen: Screen

    fun goToSchedule()

    fun goToLogin()
}