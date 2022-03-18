package ru.profi.skedda.shared.usecases

import com.soywiz.klock.DateTime
import com.soywiz.klock.days
import ru.profi.skedda.shared.SpacesRepository
import ru.profi.skedda.shared.data.internal.Booking
import ru.profi.skedda.shared.network.SkeddaApi

class LoadMyBookingsUseCase internal constructor(
    private val api: SkeddaApi,
    private val spacesRepository: SpacesRepository
) {

    suspend fun loadAccountBookingsCount(
        fromDate: Long,
        venueUserId: Long
    ): Int {
        val accountBookings = loadAccountBookings(fromDate, venueUserId)
        return accountBookings.size
    }

    private suspend fun loadAccountBookings(
        fromDate: Long,
        venueUserId: Long
    ): List<Booking> {
        val endDate = DateTime.fromUnix(fromDate) + 1.days
        val endDateTime = endDate.endOfDay.unixMillisLong
        val bookingList = api.bookingList(fromDate, endDateTime)

        return bookingList.bookings.filter { it.venueuser == venueUserId }
    }

    suspend fun loadMyBookings(
        fromDate: Long,
        venueUserId: Long
    ): List<AccountBooking> {
        val spaces = spacesRepository.loadSpaces()
        val accountBookings = loadAccountBookings(fromDate, venueUserId)

        return accountBookings.map { booking ->
            val spaceName = spaces.find { it.id == booking.spaces.first() }
            AccountBooking(
                spaceName = spaceName?.name,
                titleName = booking.title,
                startTime = DateTime.fromUnix(booking.start),
                endTime = DateTime.fromUnix(booking.end)
            )
        }
    }
}

data class AccountBooking(
    val spaceName: String?,
    val titleName: String?,
    val startTime: DateTime,
    val endTime: DateTime
)
