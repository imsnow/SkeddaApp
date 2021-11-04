package ru.profi.skedda.shared.data.internal

import kotlinx.serialization.Serializable

@Serializable
data class SpaceView(
    val id: Int,
    val name: String,
    val spaces: List<Int>,
    val venue: Int,
)