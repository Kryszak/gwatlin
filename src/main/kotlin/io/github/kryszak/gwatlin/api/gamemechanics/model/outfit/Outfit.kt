package io.github.kryszak.gwatlin.api.gamemechanics.model.outfit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for outfit object
 */
@Serializable
data class Outfit(
        val id: Int,
        val name: String,
        val icon: String,
        @SerialName("unlock_items") val unlockItems: List<Int> = listOf(),
)
