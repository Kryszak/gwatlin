package com.kryszak.gwatlin.http.serializers

import com.google.gson.*
import com.kryszak.gwatlin.api.mapinfo.model.Dimensions
import java.lang.reflect.Type


internal class DimensionsSerializer : JsonSerializer<Dimensions>, JsonDeserializer<Dimensions> {
    override fun serialize(src: Dimensions?, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement {
        return context.serialize(src?.asPair())
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext): Dimensions? {
        if (json == null || json !is JsonArray || json.size() != 2) return null
        return Dimensions(json.get(0).asFloat, json.get(1).asFloat)
    }

}