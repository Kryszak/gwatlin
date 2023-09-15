package com.kryszak.gwatlin.api.items.model.recipe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for recipe ingredient propoerty
 */
@Serializable
data class Ingredient(
        @SerialName("item_id") val itemId: Int,
        val count: Int
)
