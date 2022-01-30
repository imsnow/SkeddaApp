package ru.profi.skedda.shared.usecases

import com.soywiz.klock.days
import ru.profi.skedda.shared.Storage
import ru.profi.skedda.shared.data.BookingDuration
import ru.profi.skedda.shared.data.internal.Space
import ru.profi.skedda.shared.data.FreeSpace
import ru.profi.skedda.shared.network.SkeddaApi

class LoadFreeSpacesUseCase internal constructor(
    private val api: SkeddaApi,
    private val storage: Storage
) {

    suspend fun loadFrom(
        fromDateTime: Long,
        duration: BookingDuration
    ): List<FreeSpace> {
        val spaces = loadSpaces()

        val endDateTime = fromDateTime + 1.days.millisecondsLong
        val bookingList = api.bookingList(fromDateTime, endDateTime)

        val durationEnd = fromDateTime + duration.millis

        class BookingStart(val id: Long, val start: Long)

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