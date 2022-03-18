package ru.profi.skedda.shared.usecases

import ru.profi.skedda.shared.Storage
import ru.profi.skedda.shared.network.SkeddaApi

class BookSpaceUseCase internal constructor(
    private val api: SkeddaApi,
    private val storage: Storage
) {

    suspend fun book(id: Long, start: Long, end: Long) {
        val venue = storage.loadVenue() ?: return
        val result = api.booking(venue, id, start, end)
        println(">>> book $result")
    }
}
