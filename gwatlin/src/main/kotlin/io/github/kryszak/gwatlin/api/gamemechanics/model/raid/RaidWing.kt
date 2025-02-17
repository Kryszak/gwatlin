package io.github.kryszak.gwatlin.api.gamemechanics.model.raid

import kotlinx.serialization.Serializable

/**
 * Data model for raid wing property
 */
@Serializable
data class RaidWing(
    val id: String,
    val events: List<RaidWingEvent> = listOf(),
)
