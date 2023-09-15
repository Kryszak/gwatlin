package com.kryszak.gwatlin.api.pvp.model.season

import kotlinx.serialization.Serializable

/**
 * Data model for pvp season object
 */
@Serializable
data class PvpSeason(
        val id: String,
        val name: String,
        val start: String,
        val end: String,
        val active: Boolean,
        val divisions: List<PvpSeasonDivision>,
        val leaderboards: PvpSeasonLeaderboard
)
