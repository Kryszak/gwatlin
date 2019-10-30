package com.kryszak.gwatlin.api.pvp.model.rank

import com.google.gson.annotations.SerializedName

/**
 * Data model for pvp rank level object
 */
data class PvpRankLevel(
        @SerializedName("min_rank") val minRank: Int,
        @SerializedName("max_rank") val maxRank: Int,
        val points: Int
)
