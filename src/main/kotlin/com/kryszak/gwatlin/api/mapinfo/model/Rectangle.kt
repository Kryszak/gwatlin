package com.kryszak.gwatlin.api.mapinfo.model

/**
 * Data model for a Rectangle. A rectangle is defined by a pair of coordinates. The first coordinate represents the upper
 * left corner, while the second is the lower right corner.
 */
data class Rectangle(
    val first: Coordinates,
    val second: Coordinates
) {
    companion object {
        @JvmStatic
        fun fromPair(pair: Pair<Coordinates, Coordinates>) = Rectangle(pair.first, pair.second)
    }
    fun asPair() = first to second
}
