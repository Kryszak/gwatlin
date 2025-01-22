package io.github.kryszak.gwatlin.api.wvw.model.match

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for wvw match map objective property
 */
@Serializable
data class WvwMatchMapObjective(
        val id: String,
        val owner: String,
        val type: String,
        @SerialName("last_flipped") val lastFlipped: String,
        @SerialName("claimed_by") val claimedBy: String? = null,
        @SerialName("claimed_at") val claimedAt: String? = null,
        @SerialName("points_tick") val pointsTick: Int,
        @SerialName("points_capture") val pointsCapture: Int,
        @SerialName("guild_upgrades") val guildUpgrades: List<Int> = listOf(),
        @SerialName("yaks_delivered") val yaksDelivered: Int? = null
)
