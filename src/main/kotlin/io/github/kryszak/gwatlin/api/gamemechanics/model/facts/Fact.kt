package io.github.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonObject

/**
 * Shared interface for skill and trait facts
 */
@Serializable
sealed interface Fact {
    val text: String?
    val icon: String?

    val type: String
}
