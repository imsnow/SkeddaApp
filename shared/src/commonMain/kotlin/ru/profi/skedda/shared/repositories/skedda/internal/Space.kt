package ru.profi.skedda.shared.repositories.skedda.internal

import kotlinx.serialization.Serializable

@Serializable
data class Space(
    val children: Array<Space>,
    val id: Int,
    val index: Int,
    val info: SpaceInfo,
    val name: String,
    val nonAdminVisible: Boolean,
    val parents: Array<Space>,
    val venue: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Space

        if (!children.contentEquals(other.children)) return false
        if (id != other.id) return false
        if (index != other.index) return false
        if (info != other.info) return false
        if (name != other.name) return false
        if (nonAdminVisible != other.nonAdminVisible) return false
        if (!parents.contentEquals(other.parents)) return false
        if (venue != other.venue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = children.contentHashCode()
        result = 31 * result + id
        result = 31 * result + index
        result = 31 * result + info.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + nonAdminVisible.hashCode()
        result = 31 * result + parents.contentHashCode()
        result = 31 * result + venue
        return result
    }
}

@Serializable
data class SpaceInfo(
    val desc: String?,
    val images: Array<String>
)