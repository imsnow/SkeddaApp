package ru.profi.skedda.shared.serializers

import com.soywiz.klock.DateFormat
import com.soywiz.klock.parse
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal object SkeddaDateTimeSerializer: KSerializer<Long> {
    override val descriptor = PrimitiveSerialDescriptor("SkeddaDateTime", PrimitiveKind.STRING)
    private val formatter = DateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

    override fun deserialize(decoder: Decoder): Long {
        val value = decoder.decodeString()
        val parsedDate = formatter.parse(value)
        return parsedDate.utc.unixMillisLong
    }

    override fun serialize(encoder: Encoder, value: Long) = Unit // not used

}