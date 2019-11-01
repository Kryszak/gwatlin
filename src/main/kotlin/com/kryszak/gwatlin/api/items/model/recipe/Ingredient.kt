package com.kryszak.gwatlin.api.items.model.recipe

import com.google.gson.annotations.SerializedName

/**
 * Data model for recipe ingredient propoerty
 */
data class Ingredient(
        @SerializedName("item_id") val itemId: Int,
        val count: Int
)
