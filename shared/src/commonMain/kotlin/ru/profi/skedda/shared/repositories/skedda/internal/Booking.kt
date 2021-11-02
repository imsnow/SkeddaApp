package ru.profi.skedda.shared.repositories.skedda.internal

import kotlinx.serialization.Serializable
import ru.profi.skedda.shared.serializers.SkeddaDateTimeSerializer

@Serializable
data class Booking(
    @Serializable(with = SkeddaDateTimeSerializer::class)
    val createdDate: Long,
    @Serializable(with = SkeddaDateTimeSerializer::class)
    val start: Long,
    @Serializable(with = SkeddaDateTimeSerializer::class)
    val end: Long,
    val spaces: Array<Int>,
    val title: String,
    val id: Int,
    val type: Int,
    val recurrenceRule: String?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Booking

        if (createdDate != other.createdDate) return false
        if (start != other.start) return false
        if (end != other.end) return false
        if (!spaces.contentEquals(other.spaces)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = createdDate.hashCode()
        result = 31 * result + start.hashCode()
        result = 31 * result + end.hashCode()
        result = 31 * result + spaces.contentHashCode()
        return result
    }
}