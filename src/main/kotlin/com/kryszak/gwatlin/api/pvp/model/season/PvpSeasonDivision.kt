package com.kryszak.gwatlin.api.pvp.model.season

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for pvp season division property
 */
@Serializable
data class PvpSeasonDivision(
        val name: String,
        val flags: List<String>,
        @SerialName("large_icon") val largeIcon: String,
        @SerialName("small_icon") val smallIcon: String,
        @SerialName("pip_icon") val pipIcon: String,
        val tiers: List<Tier>,
        val leaderboards: List<PvpSeasonLeaderboard>
)
