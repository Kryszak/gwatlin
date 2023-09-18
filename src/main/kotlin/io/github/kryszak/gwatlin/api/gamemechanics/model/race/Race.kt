package io.github.kryszak.gwatlin.api.gamemechanics.model.race

import kotlinx.serialization.Serializable

/**
 * Data model for race object
 */
@Serializable
data class Race(
        val id: String,
        val name: String,
        val skills: List<Int>
)