package io.github.kryszak.gwatlin.api.pvp.model.stats

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model of account pvp stats
 */
@Serializable
data class PvpStats(
        @SerialName("pvp_rank") val pvpRank: Int,
        @SerialName("pvp_rank_points") val pvpRankPoints: Int,
        @SerialName("pvp_rank_rollovers") val pvpRankRollovers: Int,
        val aggregate: WinLossStats,
        val professions: ProfessionsStats,
        val ladders: LadderStats
)
