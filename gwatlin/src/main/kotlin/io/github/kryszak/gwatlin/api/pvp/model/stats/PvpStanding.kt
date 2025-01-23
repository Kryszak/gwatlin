package io.github.kryszak.gwatlin.api.pvp.model.stats

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for pvp standings object
 */
@Serializable
data class PvpStanding(
        val current: CurrentPvpStanding,
        val best: BestPvpStanding,
        @SerialName("season_id")
        val seasonId: String
)
