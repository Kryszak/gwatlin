package io.github.kryszak.gwatlin.api.pvp.model.rank

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for pvp rank level object
 */
@Serializable
data class PvpRankLevel(
        @SerialName("min_rank") val minRank: Int,
        @SerialName("max_rank") val maxRank: Int,
        val points: Int
)
