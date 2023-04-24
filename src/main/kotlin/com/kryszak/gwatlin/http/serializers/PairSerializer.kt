package com.kryszak.gwatlin.http.serializers

import com.google.gson.*
import java.lang.reflect.Type

internal class PairSerializer: JsonSerializer<Pair<Any, Any>> {
    override fun serialize(src: Pair<Any, Any>?, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement = src?.let {
        val jsonArray = JsonArray()
        jsonArray.add(context.serialize(it.first))
        jsonArray.add(context.serialize(it.second))
        jsonArray
    } ?: JsonNull.INSTANCE
}