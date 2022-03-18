package ru.profi.skedda.shared.data

import kotlinx.serialization.Serializable

@Serializable
internal class User(
    val id: String,
    val username: String,
    val password: String,
    val rememberMe: Boolean
)

@Serializable
internal class UserLogin(
    val login: User
)
