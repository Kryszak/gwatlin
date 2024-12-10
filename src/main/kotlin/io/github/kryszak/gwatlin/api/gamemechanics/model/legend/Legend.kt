package io.github.kryszak.gwatlin.api.gamemechanics.model.legend

import kotlinx.serialization.Serializable

/**
 * Data model for legend object
 */
@Serializable
data class Legend(
        val id: String,
        val swap: Int,
        val heal: Int,
        val elite: Int,
        val utilities: List<Int> = listOf(),
)
