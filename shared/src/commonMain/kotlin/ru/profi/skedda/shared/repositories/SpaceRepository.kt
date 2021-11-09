package ru.profi.skedda.shared.repositories

import ru.profi.skedda.shared.data.internal.Webs
import ru.profi.skedda.shared.network.SkeddaApi

class SpaceRepository internal constructor(
    private val api: SkeddaApi
) {

    suspend fun loadSpaces(): Webs {
        return api.webs()
    }
}