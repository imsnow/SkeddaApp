package ru.profi.skedda.shared.repositories.skedda.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Webs(
    val spaces: List<Space>,
    @SerialName("spaceviews")
    val spaceViews: List<SpaceView>,
)