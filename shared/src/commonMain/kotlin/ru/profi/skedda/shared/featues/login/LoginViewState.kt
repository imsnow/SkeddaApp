package ru.profi.skedda.shared.featues.login

data class LoginViewState(
    val email: String = "miha_mai@mail.ru",
    val password: String = "The22Lord22",
    val isActiveButton: Boolean = true
)