package com.kryszak.gwatlin.api.miscellaneous.model.raid

import kotlinx.serialization.Serializable

/**
 * Data model for raid object
 */
@Serializable
data class Raid(
        val id: String,
        val wings: List<RaidWing>
)
