package ru.profi.skedda.shared.network.login

import kotlinx.serialization.Serializable

@Serializable
internal data class LoginRequestPayload(
    private val login: LoginPayload
)

@Serializable
internal data class LoginPayload(
    private val username: String,
    private val password: String,
    private val rememberMe: Boolean = true,
    private val arbitraryerrors: String? = "null"
)