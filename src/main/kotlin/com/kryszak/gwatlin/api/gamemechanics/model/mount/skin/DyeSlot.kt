package com.kryszak.gwatlin.api.gamemechanics.model.mount.skin

import com.google.gson.annotations.SerializedName

/**
 * Data model for mount skin's dye slot object
 */
data class DyeSlot(
        @SerializedName("color_id") val colorId: Int,
        val material: String
)

