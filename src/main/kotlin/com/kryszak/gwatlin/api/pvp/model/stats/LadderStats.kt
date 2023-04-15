package com.kryszak.gwatlin.api.pvp.model.stats

import kotlinx.serialization.Serializable

/**
 * Data model for ladder stats object
 */
@Serializable
data class LadderStats(
        val ranked: WinLossStats?,
        val unranked: WinLossStats?
)
