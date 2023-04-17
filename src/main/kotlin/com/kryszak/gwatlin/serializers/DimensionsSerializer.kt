package com.kryszak.gwatlin.serializers

import com.kryszak.gwatlin.api.mapinfo.model.Dimensions
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.FloatArraySerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Type adapter for [com.kryszak.gwatlin.api.mapinfo.model.Dimensions], supporting serialization and deserialization
 */
object DimensionsSerializer : KSerializer<Dimensions> {
    private val delegateSerializer = FloatArraySerializer()
    override val descriptor = delegateSerializer.descriptor

    override fun serialize(encoder: Encoder, value: Dimensions) {
        encoder.encodeSerializableValue(delegateSerializer, floatArrayOf(value.x, value.y))
    }

    override fun deserialize(decoder: Decoder): Dimensions {
        val floatArray = decoder.decodeSerializableValue(delegateSerializer)
        if (floatArray.size != 2) throw SerializationException("Dimensions must consist of exactly 2 float values")
        return Dimensions(floatArray[0], floatArray[1])
    }
}