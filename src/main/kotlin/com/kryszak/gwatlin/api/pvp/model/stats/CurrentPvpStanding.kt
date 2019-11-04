package com.kryszak.gwatlin.api.pvp.model.stats

import com.google.gson.annotations.SerializedName

/**
 * Data model for current pvp standing
 */
data class CurrentPvpStanding(
        @SerializedName("total_points") val totalPoints: Int,
        val division: Int,
        val tier: Int,
        val points: Int,
        val repeats: Int,
        val rating: Int,
        val decay: Int
)
