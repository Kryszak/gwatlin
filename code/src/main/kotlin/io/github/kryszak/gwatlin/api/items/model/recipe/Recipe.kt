package io.github.kryszak.gwatlin.api.items.model.recipe

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
        val disciplines: List<String> = listOf(),
        @SerialName("min_rating") val minRating: Int,
        val flags: List<String> = listOf(),
        val ingredients: List<Ingredient> = listOf(),
        @SerialName("guild_ingredients") val guildIngredients: List<GuildIngredient> = listOf(),
        @SerialName("output_upgrade_id") val outputUpgradeId: Int? = null,
        @SerialName("chat_link") val chatLink: String
)