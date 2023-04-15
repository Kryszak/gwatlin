package com.kryszak.gwatlin.api.pvp.model.stats

import kotlinx.serialization.Serializable

/**
 * Data model for pvp statistics
 */
@Serializable
data class WinLossStats(
        val wins: Int,
        val losses: Int,
        val desertions: Int,
        val byes: Int,
        val forfeits: Int
)
