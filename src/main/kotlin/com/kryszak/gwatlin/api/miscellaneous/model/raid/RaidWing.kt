package com.kryszak.gwatlin.api.miscellaneous.model.raid

/**
 * Data model for raid wing property
 */
data class RaidWing(
        val id: String,
        val events: List<RaidWingEvent>
)
