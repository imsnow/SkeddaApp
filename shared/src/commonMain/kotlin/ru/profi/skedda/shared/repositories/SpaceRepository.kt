package ru.profi.skedda.shared.repositories

import ru.profi.skedda.shared.data.internal.Webs
import ru.profi.skedda.shared.featues.schedule.Space
import ru.profi.skedda.shared.network.SkeddaApi

class SpaceRepository internal constructor(
    private val api: SkeddaApi
) {

    suspend fun loadSpaces(): List<Space> {
        val webs = api.webs()
        val spaces = webs.spaces.map { serverSpace ->
            val id = serverSpace.id
            val name = serverSpace.name
            Space(id, name)
        }
        println(">>> spaces $spaces")
        return spaces
    }
}