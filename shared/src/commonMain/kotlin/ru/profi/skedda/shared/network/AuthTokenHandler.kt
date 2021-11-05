package ru.profi.skedda.shared.network

interface AuthTokenHandler {

    fun onTokenReceived(token: String)
}