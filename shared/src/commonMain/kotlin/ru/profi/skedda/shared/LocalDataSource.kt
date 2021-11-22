package ru.profi.skedda.shared

import ru.profi.skedda.shared.data.internal.Space

class LocalDataSource {

    private val spaces = mutableListOf<Space>()

    suspend fun loadSpaces(): List<Space>? {
        return null
    }
}