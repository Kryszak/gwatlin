package com.kryszak.gwatlin.api.pvp.model.rank

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for pvp rank object
 */
@Serializable
data class PvpRank(
    val id: Int,
    @SerialName("finisher_id") val finisherId: Int,
    val name: String,
    val icon: String,
    @SerialName("min_rank") val minRank: Int,
    @SerialName("max_rank") val maxRank: Int,
    val levels: List<PvpRankLevel>
)
