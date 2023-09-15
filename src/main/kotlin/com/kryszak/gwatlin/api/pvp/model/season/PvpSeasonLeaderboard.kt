package com.kryszak.gwatlin.api.pvp.model.season

import kotlinx.serialization.Serializable

/**
 * Data model for leaderboard object
 */
@Serializable
data class PvpSeasonLeaderboard(
        val legendary: PvpLadder,
        val guild: PvpLadder
)
