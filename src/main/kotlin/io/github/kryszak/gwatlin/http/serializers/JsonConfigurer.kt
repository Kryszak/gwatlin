package io.github.kryszak.gwatlin.http.serializers

import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.Fact
import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.FactWithNoTypeSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

/**
 * A convenience object for a [Json] instance, to
 * be used throughout this library.
 */
object JsonConfigurer {

    val json = Json {
        isLenient = true // e.g. Trait.tier is handled as an unquoted string.
        ignoreUnknownKeys = true
        serializersModule = SerializersModule {
            polymorphicDefaultDeserializer(Fact::class) {
                require(it == null)
                FactWithNoTypeSerializer()
            }
        }
    }

}
