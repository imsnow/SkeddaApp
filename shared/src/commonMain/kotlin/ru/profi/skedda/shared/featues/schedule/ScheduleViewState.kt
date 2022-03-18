package ru.profi.skedda.shared.featues.schedule

import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeTz
import com.soywiz.klock.minutes
import ru.profi.skedda.shared.data.BookingDuration
import ru.profi.skedda.shared.data.FreeSpace

data class ScheduleViewState(
    val currentDateTime: DateTimeTz = DateTime.nowLocal(),
    val durations: List<BookingDuration> = BookingDuration.values().toList(),
    val selectedDuration: BookingDuration = BookingDuration.ONE_HOUR,
    val isLoading: Boolean = true,
    val spaces: List<FreeSpace> = emptyList(),
    val accountBookingCount: Int = 0
) {

    private val roundDateTime = currentDateTime.local.round()

    val date: String = roundDateTime.format(dateFormat)

    val time: String = roundDateTime.format(timeFormat)

    val selectedFrom: Long = roundDateTime.unixMillisLong

    private fun DateTime.round(): DateTime {
        val long = this.unixMillisLong
        val min15 = 15.minutes.millisecondsLong
        val min30 = 30.minutes.millisecondsLong
        val d = (long + min15) / min30
        return DateTime(unixMillis = (d * min30).toDouble())
    }

    companion object {
        private val dateFormat = DateFormat("dd.MM")
        private val timeFormat = DateFormat("HH:mm")
    }
}
