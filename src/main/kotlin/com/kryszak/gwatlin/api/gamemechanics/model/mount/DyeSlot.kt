package com.kryszak.gwatlin.api.gamemechanics.model.mount

import com.google.gson.annotations.SerializedName

/**
 * Data model for mount skin's dye slot object
 */
data class DyeSlot(
        @SerializedName(value = "color_id") val colorId: Int,
        val material: String
)

