package io.github.kryszak.gwatlin.api.items.model.finisher

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Data model for finisher object
 */
@Serializable
data class Finisher(
        val id: Int,
        @SerialName("unlock_details") val unlockDetails: String,
        @SerialName("unlock_items") val unlockItems: List<Int> = listOf(),
        val order: Int,
        val icon: String,
        val name: String
)
