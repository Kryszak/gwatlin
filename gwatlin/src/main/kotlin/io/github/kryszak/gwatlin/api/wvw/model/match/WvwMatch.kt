package io.github.kryszak.gwatlin.api.wvw.model.match

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime

/**
 * Data model for wvw match object
 */
@Serializable
data class WvwMatch(
        val id: String,
        @Contextual
        @SerialName("start_time")
        val startTime: OffsetDateTime,
        @Contextual
        @SerialName("end_time")
        val endTime: OffsetDateTime,
        val scores: WvwMatchWorldCount,
        val worlds: WvwMatchWorldCount,
        @SerialName("all_worlds")
        val allWorlds: WvwMatchAllWorlds,
        val deaths: WvwMatchWorldCount,
        val kills: WvwMatchWorldCount,
        @SerialName("victory_points")
        val victoryPoints: WvwMatchWorldCount,
        val maps: List<WvwMatchMap> = listOf(),
        val skirmishes: List<WvwMatchSkirmish> = listOf(),
)