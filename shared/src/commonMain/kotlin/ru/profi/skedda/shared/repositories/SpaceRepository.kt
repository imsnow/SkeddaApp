package ru.profi.skedda.shared.repositories

import com.soywiz.klock.DateTime
import com.soywiz.klock.days
import ru.profi.skedda.shared.network.SkeddaApi

class SpaceRepository internal constructor(
    private val api: SkeddaApi
) {

    suspend fun loadSpaces(fromDateTime: Long): List<FreeSpace> {
        val webs = api.webs()
        val spaces = webs.spaces.map { serverSpace ->
            val id = serverSpace.id
            val name = serverSpace.name
            FreeSpace(id, name)
        }
        val bookingList = api.bookingList(fromDateTime, endDateTime)
        println(">>> booking $bookingList")
        return spaces
    }

    companion object {
        private val endDateTime = (DateTime.now() + 1.days).unixMillisLong
    }
}