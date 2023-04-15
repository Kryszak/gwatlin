package com.kryszak.gwatlin.api.guild.model.team

import com.kryszak.gwatlin.api.pvp.model.stats.WinLossStats
import kotlinx.serialization.Serializable

/**
 * Data model for unranked statistics object
 */
@Serializable
data class UnrankedStatistics(
        val unranked: WinLossStats
)
