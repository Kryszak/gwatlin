package com.kryszak.gwatlin.api.miscellaneous.model.raid

/**
 * Data model for raid object
 */
data class Raid(
        val id: String,
        val wings: List<RaidWing>
)
