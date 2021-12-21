package ru.profi.skedda.shared.data.repositories

import com.soywiz.klock.DateTime
import com.soywiz.klock.days
import ru.profi.skedda.shared.data.BookingDuration
import ru.profi.skedda.shared.data.internal.Space
import ru.profi.skedda.shared.data.internal.Venue
import ru.profi.skedda.shared.network.SkeddaApi

class SpaceRepository internal constructor(
    private val api: SkeddaApi
) {

    private var cacheVenue: Venue? = null
    private val cacheSpaces = mutableListOf<Space>()

    suspend fun loadFreeSpacesFrom(
        fromDateTime: Long,
        duration: BookingDuration
    ): List<FreeSpace> {
        val spaces = loadSpaces()
        val bookingList = api.bookingList(fromDateTime, endDateTime)

        val durationEnd = fromDateTime + duration.millis

        data class BookingStart(val id: Long, val start: Long)

        val bookings = bookingList.bookings.map {
            val id = it.spaces.first()
            BookingStart(id, it.start)
        }

        val freeSpaces = spaces.mapNotNull { serverSpace ->
            val id = serverSpace.id
            val findBooking = bookings.find { it.id == id }

            if (findBooking != null && findBooking.start < durationEnd) {
                null
            } else {
                val name = serverSpace.name
                FreeSpace(id, name)
            }
        }
        return freeSpaces
    }

    private suspend fun loadSpaces(): List<Space> {
        return if (cacheSpaces.isNotEmpty()) {
            cacheSpaces
        } else {
            val webs = api.webs()
            cacheSpaces.clear()
            cacheSpaces.addAll(webs.spaces)

            cacheVenue = webs.venue

            cacheSpaces
        }
    }

    suspend fun loadVenue(): Venue = cacheVenue ?: throw IllegalStateException("No venue")

    suspend fun book(id: Long, start: Long, end: Long) {
        val venue = loadVenue()
        val result = api.booking(venue, id, start, end)
        println(">>> book $result")
    }

    companion object {
        private val endDateTime = (DateTime.now() + 1.days).unixMillisLong
    }
}