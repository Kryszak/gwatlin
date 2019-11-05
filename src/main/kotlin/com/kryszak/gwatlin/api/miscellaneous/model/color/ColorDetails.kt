package com.kryszak.gwatlin.api.miscellaneous.model.color

/**
 * Data model for dye color details object
 */
data class ColorDetails(
        val brightness: Double,
        val contrast: Double,
        val hue: Double,
        val saturation: Double,
        val lightness: Double,
        val rgb: List<Int>
)
