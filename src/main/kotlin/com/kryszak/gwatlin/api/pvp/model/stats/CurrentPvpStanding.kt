package com.kryszak.gwatlin.api.pvp.model.stats

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for current pvp standing
 */
@Serializable
data class CurrentPvpStanding(
        @SerialName("total_points") val totalPoints: Int,
        val division: Int,
        val tier: Int,
        val points: Int,
        val repeats: Int,
        val rating: Int,
        val decay: Int
)
