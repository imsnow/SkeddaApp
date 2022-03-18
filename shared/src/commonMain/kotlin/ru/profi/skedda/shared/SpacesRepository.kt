package ru.profi.skedda.shared

import ru.profi.skedda.shared.data.internal.Space
import ru.profi.skedda.shared.network.SkeddaApi

internal class SpacesRepository(
    private val api: SkeddaApi,
    private val storage: Storage
) {

    suspend fun loadSpaces(): List<Space> {
        val cacheSpaces = storage.loadSpaces()
        if (cacheSpaces.isEmpty()) {
            loadWebsAndCache()
        }
        return storage.loadSpaces()
    }

    private suspend fun loadWebsAndCache() {
        val webs = api.webs()
        println(">>> load webs success $webs")
        storage.saveAccount(webs.venueUser)
        storage.saveSpaces(webs.spaces)
        storage.saveVenue(webs.venue)
    }
}
