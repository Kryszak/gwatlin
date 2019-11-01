package com.kryszak.gwatlin.api.items.model.recipe

import com.google.gson.annotations.SerializedName

/**
 * Data model for recipe object
 */
data class Recipe(
        val id: Int,
        val type: String,
        @SerializedName("output_item_id") val outputItemId: Int,
        @SerializedName("output_item_count") val outputItemCount: Int,
        @SerializedName("time_to_craft_ms") val timeToCraftMs: Int,
        val disciplines: List<String>,
        @SerializedName("min_rating") val minRating: Int,
        val flags: List<String>,
        val ingredients: List<Ingredient>,
        @SerializedName("guild_ingredients") val guildIngredients: List<GuildIngredient>,
        @SerializedName("output_upgrade_id") val outputUpgradeId: Int?,
        @SerializedName("chat_link") val chatLink: String
)