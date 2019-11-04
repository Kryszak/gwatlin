package com.kryszak.gwatlin.api.items.model.recipe

import com.google.gson.annotations.SerializedName

/**
 * Data model for guild ingredient recipe property
 */
data class GuildIngredient(
        @SerializedName("upgrade_id") val upgradeId: Int,
        val count: Int
)
