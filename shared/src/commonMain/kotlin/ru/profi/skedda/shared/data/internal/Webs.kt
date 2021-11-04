package ru.profi.skedda.shared.data.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Webs(
    val spaces: List<Space>,
    @SerialName("spaceviews")
    val spaceViews: List<SpaceView>,
)