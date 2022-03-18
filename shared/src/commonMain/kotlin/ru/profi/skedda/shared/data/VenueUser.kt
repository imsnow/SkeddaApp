package ru.profi.skedda.shared.data

import kotlinx.serialization.Serializable

@Serializable
data class VenueUser(
    val username: String,
    val firstName: String,
    val lastName: String,
    val id: Long
)
