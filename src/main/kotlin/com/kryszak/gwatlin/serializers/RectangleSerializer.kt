package com.kryszak.gwatlin.serializers

import com.google.gson.*
import com.kryszak.gwatlin.api.mapinfo.model.Dimensions
import com.kryszak.gwatlin.api.mapinfo.model.Rectangle
import java.lang.reflect.Type

class RectangleSerializer: JsonSerializer<Rectangle>, JsonDeserializer<Rectangle> {
    override fun serialize(src: Rectangle?, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement {
        return context.serialize(src?.asPair())
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext): Rectangle? {
        if (json == null || !json.isJsonArray || json !is JsonArray || json.size() != 2) return null
        return Rectangle(
            context.deserialize(json.get(0), Dimensions::class.java),
            context.deserialize(json.get(1), Dimensions::class.java)
        )
    }

}