package io.github.kryszak.gwatlin.api.gamemechanics.model.raid

import kotlinx.serialization.Serializable

/**
 * Data model for raid wing event object
 */
@Serializable
data class RaidWingEvent(
        val id: String,
        val type: RaidWingEventType
)
