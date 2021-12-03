package ru.profi.skedda.shared.featues.login

data class LoginViewState(
    val email: String = TestValues.email,
    val password: String = TestValues.password,
    val isActiveButton: Boolean = true
)