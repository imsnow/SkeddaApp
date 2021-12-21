package ru.profi.skedda.shared

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import ru.profi.skedda.shared.data.internal.Credential
import ru.profi.skedda.shared.data.internal.Space
import ru.profi.skedda.shared.data.internal.Venue

internal class Storage {

    private val settings: Settings = Settings()

    private val cacheSpaces = mutableListOf<Space>()
    private var cacheVenue: Venue? = null

    fun loadCredential(): Credential? {
        val email: String? = settings[KEY_EMAIL]
        val password: String? = settings[KEY_PASSWORD]

        return if (email != null && password != null) {
            Credential(email, password)
        } else null
    }

    fun saveCredentials(credential: Credential) {
        settings[KEY_EMAIL] = credential.email
        settings[KEY_PASSWORD] = credential.password
    }

    fun loadSpaces(): List<Space> {
        return cacheSpaces
    }

    fun saveSpaces(spaces: List<Space>) {
        cacheSpaces.clear()
        cacheSpaces.addAll(spaces)
    }

    fun loadVenue() = cacheVenue

    fun saveVenue(venue: Venue) {
        cacheVenue = venue
    }

    companion object {
        private const val KEY_EMAIL = "key_email"
        private const val KEY_PASSWORD = "key_password"
    }
}