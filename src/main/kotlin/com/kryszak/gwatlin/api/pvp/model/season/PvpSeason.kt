package com.kryszak.gwatlin.api.pvp.model.season

/**
 * Data model for pvp season object
 */
data class PvpSeason(
        val id: String,
        val name: String,
        val start: String,
        val end: String,
        val active: Boolean,
        val divisions: List<PvpSeasonDivision>,
        val leaderboards: PvpSeasonLeaderboard
)
