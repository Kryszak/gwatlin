package io.github.kryszak.gwatlin.api.mapinfo.model

import io.github.kryszak.gwatlin.http.serializers.RectangleSerializer
import kotlinx.serialization.Serializable

/**
 * Data model for a Rectangle. A rectangle is defined by a pair of coordinates. The first coordinate represents the upper
 * left corner, while the second is the lower right corner.
 */
@Serializable(with = RectangleSerializer::class)
data class Rectangle(
    val first: Coordinates,
    val second: Coordinates
) {
    companion object {
        /**
         * Constructs a new instance from the specified pair of coordinates
         */
        @JvmStatic
        fun fromPair(pair: Pair<Coordinates, Coordinates>) = Rectangle(pair.first, pair.second)
    }

    /**
     * Converts this instance to a pair of coordinates
     */
    fun asPair() = first to second
}
