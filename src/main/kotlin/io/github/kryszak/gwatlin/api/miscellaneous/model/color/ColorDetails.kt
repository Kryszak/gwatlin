package io.github.kryszak.gwatlin.api.miscellaneous.model.color

import kotlinx.serialization.Serializable

/**
 * Data model for dye color details object
 */
@Serializable
data class ColorDetails(
        val brightness: Double,
        val contrast: Double,
        val hue: Double,
        val saturation: Double,
        val lightness: Double,
        val rgb: List<Int>
)
