package com.kryszak.gwatlin.api.mapinfo.model

data class Rectangle(
    val first: Dimensions,
    val second: Dimensions
) {
    companion object {
        @JvmStatic
        fun fromPair(pair: Pair<Dimensions, Dimensions>) = Rectangle(pair.first, pair.second)
    }
    fun asPair() = first to second
}
