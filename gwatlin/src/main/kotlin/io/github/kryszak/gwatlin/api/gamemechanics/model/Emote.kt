package io.github.kryszak.gwatlin.api.gamemechanics.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for emote endpoint response
 */
@Serializable
data class Emote(
    val id: String,
    val commands: List<String> = listOf(),
    @SerialName("unlock_items")
    val unlockItems: List<Int> = listOf(),
)
