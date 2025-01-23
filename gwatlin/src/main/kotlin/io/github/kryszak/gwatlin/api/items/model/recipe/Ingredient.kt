package io.github.kryszak.gwatlin.api.items.model.recipe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for recipe ingredient property
 */
@Serializable
data class Ingredient(
        @SerialName("item_id") val itemId: Int,
        val count: Int
)
