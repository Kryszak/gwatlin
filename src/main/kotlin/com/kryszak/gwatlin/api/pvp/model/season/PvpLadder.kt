package com.kryszak.gwatlin.api.pvp.model.season

import kotlinx.serialization.Serializable

/**
 * Data model for pvp ladder property of pvp season object
 */
@Serializable
data class PvpLadder(
    val settings: PvpLadderSettings,
    val scorings: List<PvpLadderScorings>
)
