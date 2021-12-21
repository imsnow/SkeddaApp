package ru.profi.skedda.shared.serializers

import com.soywiz.klock.ISO8601
import com.soywiz.klock.parse
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal object SkeddaDateTimeSerializer : KSerializer<Long> {

    override val descriptor = PrimitiveSerialDescriptor("SkeddaDateTime", PrimitiveKind.STRING)
    private val formatter = ISO8601.DATETIME_UTC_COMPLETE

    override fun deserialize(decoder: Decoder): Long {
        val value = decoder.decodeString()
        val parsedDate = formatter.parse(value)
        return parsedDate.utc.unixMillisLong
    }

    override fun serialize(encoder: Encoder, value: Long) = Unit // not used

}