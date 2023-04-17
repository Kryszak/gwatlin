package com.kryszak.gwatlin.serializers

import com.kryszak.gwatlin.api.mapinfo.model.Dimensions
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.PairSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Type adapter for [com.kryszak.gwatlin.api.mapinfo.model.Dimensions], supporting serialization and deserialization
 */
object DimensionsSerializer : KSerializer<Dimensions> {
    override val descriptor = buildClassSerialDescriptor("Dimensions") {
        element("x", Float.serializer().descriptor)
        element("y", Float.serializer().descriptor)
    }
    private val delegateSerializer = PairSerializer(Float.serializer(), Float.serializer())

    override fun serialize(encoder: Encoder, value: Dimensions) {
        encoder.encodeSerializableValue(delegateSerializer, value.asPair())
    }

    override fun deserialize(decoder: Decoder): Dimensions = Dimensions.fromPair(
        decoder.decodeSerializableValue(delegateSerializer)
    )
}