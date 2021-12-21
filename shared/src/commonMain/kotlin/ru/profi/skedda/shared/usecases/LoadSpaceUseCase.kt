package ru.profi.skedda.shared.usecases

import ru.profi.skedda.shared.Storage
import ru.profi.skedda.shared.data.internal.Space

class LoadSpaceUseCase internal constructor(
    private val storage: Storage
){

    suspend fun loadById(id: Long): Space {
        val spaces = storage.loadSpaces()
        return spaces.first { it.id == id }
    }
}