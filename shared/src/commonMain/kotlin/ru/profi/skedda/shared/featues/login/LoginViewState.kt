package ru.profi.skedda.shared.featues.login

data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val isActiveButton: Boolean = false
)