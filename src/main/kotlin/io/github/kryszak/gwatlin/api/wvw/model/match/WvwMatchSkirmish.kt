package io.github.kryszak.gwatlin.api.wvw.model.match

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for wvw match skirmish property
 */
@Serializable
data class WvwMatchSkirmish(
        val id: Int,
        val scores: WvwMatchWorldCount,
        @SerialName("map_scores") val mapScores: List<WvwMatchSkirmishScore>
)
