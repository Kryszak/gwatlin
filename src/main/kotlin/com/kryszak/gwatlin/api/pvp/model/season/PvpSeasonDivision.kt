package com.kryszak.gwatlin.api.pvp.model.season

import com.google.gson.annotations.SerializedName

/**
 * Data model for pvp season division property
 */
data class PvpSeasonDivision(
        val name: String,
        val flags: List<String>,
        @SerializedName("large_icon") val largeIcon: String,
        @SerializedName("small_icon") val smallIcon: String,
        @SerializedName("pip_icon") val pipIcon: String,
        val tiers: List<Tier>,
        val leaderboards: List<PvpSeasonLeaderboard>
)
