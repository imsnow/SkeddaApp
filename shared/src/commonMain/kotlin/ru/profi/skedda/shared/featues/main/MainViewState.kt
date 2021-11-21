package ru.profi.skedda.shared.featues.main

data class MainViewState(
    val type: LoginType = LoginType.PREPARING
)

enum class LoginType {
    PREPARING,
    NEED_LOGIN,
    HAS_USER
}