package io.github.kryszak.gwatlin.api.pvp.model.stats

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for best pvp standing
 */
@Serializable
data class BestPvpStanding(
        @SerialName("total_points") val totalPoints: Int,
        val division: Int,
        val tier: Int,
        val points: Int,
        val repeats: Int
)
