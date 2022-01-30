package ru.profi.skedda.shared.router

import ru.profi.skedda.shared.data.BookingDuration

sealed class Screen {

    abstract val route: String

    object Main : Screen() {
        override val route = "main"
    }

    object Schedule : Screen() {
        override val route = "schedule"
    }

    object Login : Screen() {
        override val route = "login"
    }

    object Booking : Screen() {
        override val route: String
            get() = "booking/{id}/{from}/{duration}"

        fun routeTo(
            id: Long,
            from: Long,
            duration: BookingDuration
        ) = "booking/$id/$from/$duration"
    }

    object Account : Screen() {
        override val route: String
            get() = "account"
    }
}