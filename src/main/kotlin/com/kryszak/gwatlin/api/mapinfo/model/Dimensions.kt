package com.kryszak.gwatlin.api.mapinfo.model

import com.kryszak.gwatlin.serializers.DimensionsSerializer
import kotlinx.serialization.Serializable

/**
 * Data model for Dimensions. A dimension is something with a size in both the X and Y directions.
 */
@Serializable(with = DimensionsSerializer::class)
data class Dimensions(
    val x: Float,
    val y: Float
) {
    companion object {
        /**
         * Constructs a new instance from the specified pair of floats
         */
        @JvmStatic
        fun fromPair(pair: Pair<Float, Float>) = Dimensions(pair.first, pair.second)
    }
    /**
     * Converts this instance to a pair of floats
     */
    fun asPair() = x to y
}

/**
 * Data model for Coordinates. A coordinate is defined by one value each on the X and Y axis.
 */
typealias Coordinates = Dimensions