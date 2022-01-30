package ru.profi.skedda.shared.usecases

import ru.profi.skedda.shared.network.SkeddaApi

class LoadMyBookingsUseCase internal constructor(
    private val api: SkeddaApi
) {

    suspend fun loadMyBookings(): Unit {

    }
}