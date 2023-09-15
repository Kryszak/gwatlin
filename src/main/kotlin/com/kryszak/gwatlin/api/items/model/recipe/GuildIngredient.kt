package com.kryszak.gwatlin.api.items.model.recipe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for guild ingredient recipe property
 */
@Serializable
data class GuildIngredient(
        @SerialName("upgrade_id") val upgradeId: Int,
        val count: Int
)
