package ru.profi.skedda.shared.repositories

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import ru.profi.skedda.shared.network.SkeddaApi

internal class UserRepository(
    private val api: SkeddaApi
) {

    private val settings: Settings = Settings()

    suspend fun loadUser(): Boolean {
        val email: String? = settings[KEY_EMAIL]
        val password: String? = settings[KEY_PASSWORD]

        return if (email != null && password != null) {
            val result = login(email, password)
            println(">>> load user result $result")
            result
        } else false
    }

    suspend fun login(email: String, password: String): Boolean {
        return try {
            val userLogin = api.login(email, password)
            println(">>> login success ${userLogin.login}")
            settings[KEY_EMAIL] = email
            settings[KEY_PASSWORD] = password
            true
        } catch (e: Exception) {
            println(">>> login error $e")
            false
        }
    }

    companion object {
        private const val KEY_EMAIL = "key_email"
        private const val KEY_PASSWORD = "key_password"
    }
}