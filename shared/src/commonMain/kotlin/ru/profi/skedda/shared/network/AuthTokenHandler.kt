package ru.profi.skedda.shared.network

interface AuthTokenHandler {

    val token: String?

    fun onTokenReceived(token: String)
}