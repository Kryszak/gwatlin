package com.kryszak.gwatlin.api.mapinfo.model

data class Dimensions(
    val x: Float,
    val y: Float
) {
    companion object {
        fun fromPair(pair: Pair<Float, Float>) = Dimensions(pair.first, pair.second)
    }
    fun asPair() = x to y
}

typealias Coordinates = Dimensions