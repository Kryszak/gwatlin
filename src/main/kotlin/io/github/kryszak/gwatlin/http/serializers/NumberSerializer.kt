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
        require(element is JsonPrimitive) { "Expected a json primitive, got  $element" }
        val content = element.content
        require(!element.isString) { "String element not supported: '$content'" }
        return if (content.toIntOrNull() != null) Int.serializer()
        else Double.serializer()
    }
}
