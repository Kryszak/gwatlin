package io.github.kryszak.gwatlin.http.serializers

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * A convenience object for [Json], and to help the
 * library to use the same instance of it through out.
 */
object JsonUtil {

    val json = Json {
        isLenient = true // e.g. Trait.tier is handled as an unquoted string.
        ignoreUnknownKeys = true
    }

}
g