package com.kryszak.gwatlin.api.guild.model.team

import com.kryszak.gwatlin.api.pvp.model.WinLossStats

/**
 * Data model for unranked statistics object
 */
data class UnrankedStatistics(
        val unranked: WinLossStats
)
