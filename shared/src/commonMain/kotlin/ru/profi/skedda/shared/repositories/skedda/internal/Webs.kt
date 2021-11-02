package ru.profi.skedda.shared.repositories.skedda.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Webs(
    val spaces: Array<Space>,
    @SerialName("spaceviews")
    val spaceViews: Array<SpaceView>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Webs

        if (!spaces.contentEquals(other.spaces)) return false
        if (!spaceViews.contentEquals(other.spaceViews)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = spaces.contentHashCode()
        result = 31 * result + spaceViews.contentHashCode()
        return result
    }
}