package com.kryszak.gwatlin.api.pvp.model.season

/**
 * Data model for pvp ladder scorings
 */
data class PvpLadderScorings(
        val id: String,
        val type: String,
        val description: String,
        val name: String,
        val ordering: String
)
