package com.kryszak.gwatlin.api.mapinfo.model

/**
 * Data model for Dimensions. A dimension is something with a size in both the X and Y directions.
 */
data class Dimensions(
    val x: Float,
    val y: Float
) {
    companion object {
        @JvmStatic
        fun fromPair(pair: Pair<Float, Float>) = Dimensions(pair.first, pair.second)
    }
    fun asPair() = x to y
}

/**
 * Data model for Coordinates. A coordinate is defined by one value each on the X and Y axis.
 */
typealias Coordinates = Dimensions