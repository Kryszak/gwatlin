package io.github.kryszak.gwatlin.http.serializers

import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.Fact
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import java.time.OffsetDateTime

/**
 * A convenience object for a [Json] instance, to
 * be used throughout this library.
 */
object JsonConfigurer {

    val json = Json {
        isLenient = true // e.g. Trait.tier is handled as an unquoted string.
        ignoreUnknownKeys = true
        explicitNulls = true
        serializersModule = SerializersModule {
            polymorphicDefaultDeserializer(Fact::class) {
                require(it == null)
                FactWithNoTypeSerializer()
            }
            contextual<Number>(NumberSerializer())
            contextual<OffsetDateTime>(OffsetDateTimeSerializer())
        }
    }

}
