package ru.profi.skedda.shared.repositories

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import ru.profi.skedda.shared.network.SkeddaApi

internal class UserRepository(
    private val api: SkeddaApi
){

    private val settings: Settings = Settings()

    suspend fun loadUser(): User? {

        println(">>> before ${settings.get<String>(KEY_TOKEN)}")

        settings[KEY_TOKEN] = "huilo"

        println(">>> after ${settings.get<String>(KEY_TOKEN)}")

        return null
    }

    suspend fun login(email: String, password: String): User? {
        return try {
            val userLogin = api.login(email, password)
            userLogin.login
        } catch (e: Exception) {
            null
        }
    }

    companion object {
        private const val KEY_TOKEN = "token"
    }
}