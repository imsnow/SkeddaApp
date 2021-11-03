package ru.profi.skedda.shared.repositories.skedda.internal

import kotlinx.serialization.Serializable

@Serializable
data class Space(
    val children: List<Space>,
    val id: Int,
    val index: Int,
    val info: SpaceInfo,
    val name: String,
    val nonAdminVisible: Boolean,
    val parents: List<Space>,
    val venue: Int,
)

@Serializable
data class SpaceInfo(
    val desc: String?,
    val images: List<String>
)