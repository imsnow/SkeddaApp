package ru.profi.skedda.shared.router

sealed class Screen {

    abstract val route: String

    object Main : Screen() {
        override val route = "main"
    }

    object Schedule: Screen() {
        override val route = "schedule"
    }

    object Login : Screen() {
        override val route = "login"
    }
}