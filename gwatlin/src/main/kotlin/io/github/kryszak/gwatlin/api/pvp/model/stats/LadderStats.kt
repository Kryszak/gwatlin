package io.github.kryszak.gwatlin.api.pvp.model.stats

import kotlinx.serialization.Serializable

/**
 * Data model for ladder stats object
 */
@Serializable
data class LadderStats(
        val ranked: WinLossStats? = null,
        val unranked: WinLossStats? = null,
)
