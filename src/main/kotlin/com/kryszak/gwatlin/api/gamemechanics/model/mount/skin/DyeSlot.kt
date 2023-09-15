package com.kryszak.gwatlin.api.gamemechanics.model.mount.skin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for mount skin's dye slot object
 */
@Serializable
data class DyeSlot(
        @SerialName("color_id") val colorId: Int,
        val material: String
)

