package com.kryszak.gwatlin.api.pvp.model.season

/**
 * Data model for leaderboard object
 */
data class PvpSeasonLeaderboard(
        val legendary: PvpLadder,
        val guild: PvpLadder
)
