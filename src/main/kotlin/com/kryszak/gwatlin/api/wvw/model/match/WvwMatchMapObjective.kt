package com.kryszak.gwatlin.api.wvw.model.match

import com.google.gson.annotations.SerializedName

/**
 * Data model for wvw match map objective property
 */
data class WvwMatchMapObjective(
        val id: String,
        val owner: String,
        val type: String,
        @SerializedName("last_flipped") val lastFlipped: String,
        @SerializedName("claimed_by") val claimedBy: String,
        @SerializedName("claimed_at") val claimedAt: String,
        @SerializedName("points_tick") val pointsTick: Int,
        @SerializedName("points_capture") val pointsCapture: Int,
        @SerializedName("guild_upgrades") val guildUpgrades: List<Int>,
        @SerializedName("yaks_delivered") val yaksDelivered: Int
)
