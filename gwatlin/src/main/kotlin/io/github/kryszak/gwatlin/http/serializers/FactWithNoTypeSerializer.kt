package io.github.kryszak.gwatlin.http.serializers

import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.Fact
import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.Percent
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.modules.SerializersModule

/**
 * Register into [SerializersModule] to support Facts in JSON
 * without type property. It's likely an error in API implementation,
 * see e.g. [Nefarious Favor](https://api.guildwars2.com/v2/skills?ids=40813&v=latest).
 *
 */
internal class FactWithNoTypeSerializer : KSerializer<Fact> {
    override val descriptor: SerialDescriptor = Fact.serializer().descriptor
    override fun deserialize(decoder: Decoder): Fact {
        require(decoder is JsonDecoder)
        val keys = decoder.decodeJsonElement()
            .jsonObject.entries.map { it.key }
            .toSet()
        return chooseSerializer(keys).deserialize(decoder)
    }

    override fun serialize(encoder: Encoder, value: Fact) {
        Fact.serializer().serialize(encoder, value)
    }

    private fun chooseSerializer(keys: Set<String>): KSerializer<out Fact> {
        return when(keys) {
            KEYS_OF_PERCENTAGE -> Percent.serializer()
            else -> throw IllegalArgumentException(
                "Can't figure out what Fact type to use based on keys: '$keys'"
            )
        }
    }

    companion object {
        private val KEYS_OF_PERCENTAGE = setOf("text", "icon", "percent")
    }

}
