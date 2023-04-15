package com.kryszak.gwatlin.api.items.model.recipe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for recipe object
 */
@Serializable
data class Recipe(
        val id: Int,
        val type: String,
        @SerialName("output_item_id") val outputItemId: Int,
        @SerialName("output_item_count") val outputItemCount: Int,
        @SerialName("time_to_craft_ms") val timeToCraftMs: Int,
        val disciplines: List<String>,
        @SerialName("min_rating") val minRating: Int,
        val flags: List<String>,
        val ingredients: List<Ingredient>,
        @SerialName("guild_ingredients") val guildIngredients: List<GuildIngredient>,
        @SerialName("output_upgrade_id") val outputUpgradeId: Int?,
        @SerialName("chat_link") val chatLink: String
)