package com.kryszak.gwatlin.api.pvp.model.rank

import com.google.gson.annotations.SerializedName

/**
 * Data model for pvp rank object
 */
data class PvpRank(
    val id: Int,
    @SerializedName("finisher_id") val finisherId: Int,
    val name: String,
    val icon: String,
    @SerializedName("min_rank") val minRank: Int,
    @SerializedName("max_rank") val maxRank: Int,
    val levels: List<PvpRankLevel>
)
