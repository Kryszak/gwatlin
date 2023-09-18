package io.github.kryszak.gwatlin.api.guild.model.upgrade

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for cost property of guild upgrade object
 */
@Serializable
data class UpgradeCost(
        val type: String,
        val name: String,
        val count: Int,
        @SerialName("item_id") val itemId: Int? = null
)
