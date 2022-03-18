package ru.profi.skedda.shared.data.internal

import kotlinx.serialization.Serializable

@Serializable
data class Space(
//    val children: List<Space>,
    val id: Long,
//    val index: Int,
    val info: SpaceInfo,
    val name: String,
//    val nonAdminVisible: Boolean,
//    val parents: List<Space>,
//    val venue: Int,
)

@Serializable
data class SpaceInfo(
    val desc: String?,
    val images: List<String>
)
