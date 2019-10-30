package com.kryszak.gwatlin.api.pvp.model

/**
 * Data model for pvp statistics
 */
data class WinLossStats(
        val wins: Int,
        val losses: Int,
        val desertions: Int,
        val byes: Int,
        val forfeits: Int
)
