package ru.profi.skedda.shared.data.internal

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
    val spaces: List<Int>,
    val title: String?,
    val id: Int,
    val type: Int,
    val recurrenceRule: String?,
)