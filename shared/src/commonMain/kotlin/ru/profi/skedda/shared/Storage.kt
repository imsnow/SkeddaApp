package ru.profi.skedda.shared

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import ru.profi.skedda.shared.data.VenueUser
import ru.profi.skedda.shared.data.internal.Credential
import ru.profi.skedda.shared.data.internal.Space
import ru.profi.skedda.shared.data.internal.Venue

internal class Storage {

    private val settings: Settings = Settings()

    private val cacheSpaces = mutableListOf<Space>()
    private var cacheVenue: Venue? = null
    private val cacheVenueUser = mutableListOf<VenueUser>()

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

    fun saveSpaces(spaces: List<Space>) = cacheSpaces.setAll(spaces)

    fun loadVenue() = cacheVenue

    fun saveVenue(venue: Venue) {
        cacheVenue = venue
    }

    fun loadAccount() = cacheVenueUser.first()

    fun saveAccount(accounts: List<VenueUser>) = cacheVenueUser.setAll(accounts)

    private fun <T : Any> MutableList<T>.setAll(items: List<T>) {
        this.clear()
        this.addAll(items)
    }

    companion object {
        private const val KEY_EMAIL = "key_email"
        private const val KEY_PASSWORD = "key_password"
    }
}
