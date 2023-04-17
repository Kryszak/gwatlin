package com.kryszak.gwatlin.serializers

import com.kryszak.gwatlin.api.mapinfo.model.Dimensions
import com.kryszak.gwatlin.api.mapinfo.model.Rectangle
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ArraySerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.PairSerializer
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Type adapter for [com.kryszak.gwatlin.api.mapinfo.model.Rectangle], supporting serialization and deserialization
 */
object RectangleSerializer : KSerializer<Rectangle> {
    private val delegateSerializer = ListSerializer(Dimensions.serializer())
    override val descriptor = delegateSerializer.descriptor

    override fun serialize(encoder: Encoder, value: Rectangle) {
        encoder.encodeSerializableValue(delegateSerializer, listOf(value.first, value.second))
    }

    override fun deserialize(decoder: Decoder): Rectangle {
        val dimensions = decoder.decodeSerializableValue(delegateSerializer)
        if (dimensions.size != 2) throw SerializationException("Rectangles must consist of exactly 2 dimension arrays")
        return Rectangle(dimensions[0], dimensions[1])
    }
}