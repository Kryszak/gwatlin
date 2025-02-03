package io.github.kryszak.gwatlin.api.shared

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for dye slot object
 */
@Serializable
data class DyeSlot(
        @SerialName("color_id") val colorId: Int,
        val material: String
)

