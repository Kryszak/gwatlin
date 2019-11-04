package com.kryszak.gwatlin.api.pvp.model.stats

import com.google.gson.annotations.SerializedName

/**
 * Data model of account pvp stats
 */
data class PvpStats(
        @SerializedName("pvp_rank") val pvpRank: Int,
        @SerializedName("pvp_rank_points") val pvpRankPoints: Int,
        @SerializedName("pvp_rank_rollovers") val pvpRankRollovers: Int,
        val aggregate: WinLossStats,
        val professions: ProfessionsStats,
        val ladders: LadderStats
)
