package io.github.kryszak.gwatlin.api.wvw.model.match.overview

import kotlinx.serialization.SerialName
import io.github.kryszak.gwatlin.api.wvw.model.match.WvwMatchMap
import io.github.kryszak.gwatlin.api.wvw.model.match.WvwMatchSkirmish
import io.github.kryszak.gwatlin.api.wvw.model.match.WvwMatchWorldCount
import kotlinx.serialization.Serializable

/**
 * Data model for scores sub-endpoint
 */
@Serializable
data class WvwWorldScores(
        val id: String,
        val maps: List<WvwMatchMap>,
        val scores: WvwMatchWorldCount,
        val skirmishes: List<WvwMatchSkirmish>,
        @SerialName("victory_points") val victoryPoints: WvwMatchWorldCount
)
