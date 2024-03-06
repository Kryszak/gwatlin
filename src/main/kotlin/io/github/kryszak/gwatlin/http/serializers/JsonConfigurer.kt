package io.github.kryszak.gwatlin.http.serializers

import kotlinx.serialization.json.Json

/**
 * A convenience object for a [Json] instance, to
 * be used throughout this library.
 */
object JsonConfigurer {

    val json = Json {
        isLenient = true // e.g. Trait.tier is handled as an unquoted string.
        ignoreUnknownKeys = true
    }

}
