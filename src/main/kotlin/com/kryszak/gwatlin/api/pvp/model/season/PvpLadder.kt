package com.kryszak.gwatlin.api.pvp.model.season

/**
 * Data model for pvp ladder property of pvp season object
 */
data class PvpLadder(
    val settings: PvpLadderSettings,
    val scorings: PvpLadderScorings
)
