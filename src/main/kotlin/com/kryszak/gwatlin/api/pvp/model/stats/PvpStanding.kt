package com.kryszak.gwatlin.api.pvp.model.stats

import com.google.gson.annotations.SerializedName

/**
 * Data model for pvp standings object
 */
data class PvpStanding(
        val current: CurrentPvpStanding,
        val best: BestPvpStanding,
        @SerializedName("season_id") val seasonId: String
)
