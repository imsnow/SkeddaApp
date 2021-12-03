package ru.profi.skedda.shared.repositories

import com.soywiz.klock.DateTime
import com.soywiz.klock.days
import ru.profi.skedda.shared.data.BookingDuration
import ru.profi.skedda.shared.data.internal.Space
import ru.profi.skedda.shared.data.internal.Webs
import ru.profi.skedda.shared.network.SkeddaApi

class SpaceRepository internal constructor(
    private val api: SkeddaApi
) {

    private val cacheSpaces = mutableListOf<Space>()

    suspend fun loadFreeSpacesFrom(
        fromDateTime: Long,
        duration: BookingDuration
    ): List<FreeSpace> {
        val spaces = loadSpaces()
        val bookingList = api.bookingList(fromDateTime, endDateTime)

        val durationEnd = fromDateTime + duration.millis
        println(">>> duration end $durationEnd")

        data class BookingStart(val id: Long, val start: Long)

        val bookings = bookingList.bookings.map {
            val id = it.spaces.first()
            BookingStart(id, it.start)
        }

        val freeSpaces = spaces.mapNotNull { serverSpace ->
            val id = serverSpace.id
            val findBooking = bookings.find { it.id == id }
            println(">>> booking $findBooking")
            if (findBooking != null && findBooking.start < durationEnd) {
                null
            } else {
                val name = serverSpace.name
                FreeSpace(id, name)
            }
//            if (find.start >= durationEnd) {
//                val name = serverSpace.name
//                FreeSpace(id, name)
//            } else null
        }

//        println(">>> booking $bookingList")
        return freeSpaces
    }

    private suspend fun loadSpaces(): List<Space> {
        return if (cacheSpaces.isNotEmpty()) {
            cacheSpaces
        } else {
            val webs = api.webs()
            cacheSpaces.clear()
            cacheSpaces.addAll(webs.spaces)
            cacheSpaces
        }
    }

    companion object {
        private val endDateTime = (DateTime.now() + 1.days).unixMillisLong
    }
}