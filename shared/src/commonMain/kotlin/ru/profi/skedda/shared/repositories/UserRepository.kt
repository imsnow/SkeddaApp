package ru.profi.skedda.shared.repositories

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import ru.profi.skedda.shared.network.AuthTokenHandler
import ru.profi.skedda.shared.network.SkeddaApi

internal class UserRepository(
    private val api: SkeddaApi
): AuthTokenHandler {

    private val settings: Settings = Settings()

    override val token: String?
        get() = settings[KEY_TOKEN]

    init {
        api.setTokenHandler(this)
    }

    fun loadUser(): String? {
        println(">>> load ${settings.get<String>(KEY_TOKEN)}")
        return settings[KEY_TOKEN]
    }

    override fun onTokenReceived(token: String) {
        println(">>> save $token")
        settings[KEY_TOKEN] = token
    }

    suspend fun login(email: String, password: String) {
        try {
            val userLogin = api.login(email, password)
            println(">>> login success ${userLogin.login}")
        } catch (e: Exception) {
            println(">>> login error $e")
        }
    }

    companion object {
        private const val KEY_TOKEN = "token"
    }
}