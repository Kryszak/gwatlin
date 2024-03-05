package io.github.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTransformingSerializer
import kotlinx.serialization.json.jsonObject

/**
 * Data model for traited fact skill property.
 *
 * _Important!_ When serializing, serialize with [TraitedFactUnwrapSerializer]
 * to comply with GW2 API.
 *
 */
@Serializable
data class TraitedFact(
    @SerialName("requires_trait")
    val requiresTrait: Int,
    val overrides: Int? = null,
    val fact: Fact,
) {
    class TraitedFactUnwrapSerializer : JsonTransformingSerializer<TraitedFact>(serializer()) {
        private fun split(element: JsonElement) = element.jsonObject.entries
            .groupBy { (k, _) -> k == "requires_trait" || k == "overrides" }
            .mapValues { (_, v) -> v.associate { it.toPair() } }
            .let { it[true] to it[false] }

        override fun transformSerialize(element: JsonElement): JsonElement {
            val (traitedFact, fact) = split(element)
            val entries = requireNotNull(traitedFact).toMutableMap()
            requireNotNull(fact).entries.single().value.jsonObject.entries
                .forEach { (k, v) -> entries[k] = v }
            return JsonObject(entries)
        }

        override fun transformDeserialize(element: JsonElement): JsonElement {
            val (traitedFact, fact) = split(element)
            val entries = requireNotNull(traitedFact).toMutableMap()
            entries.put("fact", JsonObject(requireNotNull(fact)))
            return JsonObject(entries)
        }

    }

}
