package io.github.kryszak.gwatlin.api.pvp.model.season

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for pvp season division property
 */
@Serializable
data class PvpSeasonDivision(
        val name: String,
        val flags: List<String> = listOf(),
        @SerialName("large_icon") val largeIcon: String? = null,
        @SerialName("small_icon") val smallIcon: String? = null,
        @SerialName("pip_icon") val pipIcon: String? = null,
        val tiers: List<Tier> = listOf(),
        val leaderboards: List<PvpSeasonLeaderboard> = listOf()
)
