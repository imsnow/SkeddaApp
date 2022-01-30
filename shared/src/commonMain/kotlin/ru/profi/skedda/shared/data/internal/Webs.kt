package ru.profi.skedda.shared.data.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.profi.skedda.shared.data.VenueUser

@Serializable
data class Webs(
    @SerialName("venueuser")
    val venueUser: List<VenueUser>,
    val spaces: List<Space>,
    @SerialName("spaceviews")
    val spaceViews: List<SpaceView>,
    @SerialName("web")
    val venue: Venue
)

@Serializable
data class Venue(
    val venue: String, // 78777
    val venueuser: String // 438910
)
