package com.kryszak.gwatlin.api.gamemechanics.model.legend

/**
 * Data model for legend object
 */
data class Legend(
        val id: String,
        val swap: Int,
        val heal: Int,
        val elite: Int,
        val utilities: List<Int>
)
