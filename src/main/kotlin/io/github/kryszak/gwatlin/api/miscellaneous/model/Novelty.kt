package io.github.kryszak.gwatlin.api.miscellaneous.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for novelty object
 */
@Serializable
data class Novelty(
        val id: Int,
        val name: String,
        val description: String,
        val icon: String,
        val slot: String,
        @SerialName("unlock_item") val unlockItem: List<Int>?
)
