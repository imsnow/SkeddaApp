package ru.profi.skedda.shared.usecases

import com.soywiz.klock.*
import ru.profi.skedda.shared.ExistBooking
import ru.profi.skedda.shared.SpacesRepository
import ru.profi.skedda.shared.Storage
import ru.profi.skedda.shared.data.BookingDuration
import ru.profi.skedda.shared.data.internal.Space
import ru.profi.skedda.shared.data.FreeSpace
import ru.profi.skedda.shared.network.SkeddaApi

class LoadFreeSpacesUseCase internal constructor(
    private val api: SkeddaApi,
    private val spacesRepository: SpacesRepository
) {

    suspend fun loadFrom(
        fromDateTime: Long,
        duration: BookingDuration
    ): List<FreeSpace> {
        val spaces = spacesRepository.loadSpaces()

        val fromDate = DateTime.fromUnix(fromDateTime) + 1.days
        val endDateTime = fromDate.endOfDay.unixMillisLong
        val bookingList = api.bookingList(fromDateTime, endDateTime)

        val durationEnd = DateTime.fromUnix(fromDateTime + duration.millis)

//        val dayKey = fromDate.format("YYYY-MM-dd")
//        val recurrenceBookIds = bookingList.bookingslist
//            .idx[dayKey]
//            ?.jsonArray
//            ?.map { it.jsonPrimitive.long } ?: emptyList()
//        println(">>> recurr idx $recurrenceBookIds")


        val existBookingList = bookingList.bookings.map {
            val id = it.spaces.first()
//            if (recurrenceBookIds.contains(id)) {
//                null
//            } else {
            val startTime = DateTime.fromUnix(it.start).time
            val endTime = DateTime.fromUnix(it.end).time
            println(">>> id $id time $startTime space ${spaces.find { it.id == id }?.name}")

            ExistBooking(id, startTime = startTime, endTime = endTime)
//            }
        }

        val freeSpaces = spaces.mapNotNull { space ->
            val currentSpaceId = space.id
            val currentSpaceBookings = existBookingList.filter { it.id == currentSpaceId }

            if (currentSpaceBookings.hasBookings(start = fromDate.time, end = durationEnd.time)) {
                println(">>> has bookings ${space.name}")
                null
            } else {
                val name = space.name
                FreeSpace(currentSpaceId, name)
            }
        }
        return freeSpaces
    }

    private fun ExistBooking.inSearchRange(start: Time, end: Time): Boolean {
        if (startTime >= start && startTime < end) return true
        if (endTime > start && endTime <= end) return true
        return false
    }

    private fun List<ExistBooking>.hasBookings(start: Time, end: Time): Boolean {
        this.forEach { existBooking ->
            val inSearchRange = existBooking.inSearchRange(start, end)
            if (inSearchRange) return true
        }
        return false
    }
}