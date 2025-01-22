package io.github.kryszak.gwatlin.http.serializers

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive

/**
 * Selects either [Int] or [Double] based on the JsonElement's content.
 */
class NumberSerializer : JsonContentPolymorphicSerializer<Number>(Number::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Number> {
        return if (element.requireContent().isInteger())
            Int.serializer()
        else Double.serializer()
    }

    companion object {
        private fun JsonElement.requireContent(): String {
            require(this is JsonPrimitive) { "Expected a json primitive, got  $this" }
            require(!isString) { "String element not supported: '$content'" }
            return content
        }

        private fun String.isInteger() = toIntOrNull() != null
    }

}
