package com.kryszak.gwatlin.serializers

import com.kryszak.gwatlin.api.mapinfo.model.Dimensions
import com.kryszak.gwatlin.api.mapinfo.model.Rectangle
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.PairSerializer
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Type adapter for [com.kryszak.gwatlin.api.mapinfo.model.Rectangle], supporting serialization and deserialization
 */
object RectangleSerializer : KSerializer<Rectangle> {
    private val delegateSerializer = PairSerializer(Dimensions.serializer(), Dimensions.serializer())
    override val descriptor = buildClassSerialDescriptor("Rectangle") {
        element("first", delegateSerializer.descriptor)
        element("second", delegateSerializer.descriptor)
    }

    override fun serialize(encoder: Encoder, value: Rectangle) {
        encoder.encodeSerializableValue(delegateSerializer, value.asPair())
    }

    override fun deserialize(decoder: Decoder): Rectangle = Rectangle.fromPair(
        decoder.decodeSerializableValue(delegateSerializer)
    )
}