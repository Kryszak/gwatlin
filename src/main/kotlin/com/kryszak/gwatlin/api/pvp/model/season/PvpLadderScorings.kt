package com.kryszak.gwatlin.api.pvp.model.season

import kotlinx.serialization.Serializable

/**
 * Data model for pvp ladder scorings
 */
@Serializable
data class PvpLadderScorings(
        val id: String,
        val type: String,
        val description: String,
        val name: String,
        val ordering: String
)
