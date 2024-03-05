package io.github.kryszak.gwatlin.http.serializers

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object JsonUtil {
    inline fun <reified T> encode(t: T): String =
        json.encodeToString(t)

    inline fun <reified T> decode(txt: String): T =
        json.decodeFromString<T>(txt)


    val json = Json {
        isLenient = true // e.g. Trait.tier is handled as an unquoted string.
        ignoreUnknownKeys = true
    }

}
