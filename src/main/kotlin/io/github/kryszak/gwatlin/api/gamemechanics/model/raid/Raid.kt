package io.github.kryszak.gwatlin.api.gamemechanics.model.raid

import kotlinx.serialization.Serializable

/**
 * Data model for raid object
 */
@Serializable
data class Raid(
    val id: String,
    val wings: List<RaidWing> = listOf(),
)
