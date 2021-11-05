package ru.profi.skedda.shared.repositories

import ru.profi.skedda.shared.network.SkeddaApi

class SpaceRepository internal constructor(
    private val api: SkeddaApi
) {

    suspend fun loadSpaces() {
        val spaces = api.webs()
    }
}