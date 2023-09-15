package com.kryszak.gwatlin.api.miscellaneous.model.raid

import kotlinx.serialization.Serializable

/**
 * Data model for raid wing event object
 */
@Serializable
data class RaidWingEvent(
        val id: String,
        val type: String
)
