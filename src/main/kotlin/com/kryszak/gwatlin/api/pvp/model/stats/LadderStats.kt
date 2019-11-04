package com.kryszak.gwatlin.api.pvp.model.stats

/**
 * Data model for ladder stats object
 */
data class LadderStats(
        val ranked: WinLossStats?,
        val unranked: WinLossStats?
)
