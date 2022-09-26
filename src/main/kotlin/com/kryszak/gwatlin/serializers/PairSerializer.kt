package com.kryszak.gwatlin.serializers

import com.google.gson.*
import java.lang.reflect.Type

class PairSerializer: JsonSerializer<Pair<Any, Any>> {
    override fun serialize(src: Pair<Any, Any>?, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement {
        if (src == null) return JsonNull.INSTANCE
        val jsonArray = JsonArray()
        jsonArray.add(context.serialize(src.first))
        jsonArray.add(context.serialize(src.second))
        return jsonArray
    }
}