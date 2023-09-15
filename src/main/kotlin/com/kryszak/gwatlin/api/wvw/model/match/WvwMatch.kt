package com.kryszak.gwatlin.api.wvw.model.match

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for wvw match object
 */
@Serializable
data class WvwMatch(
        val id: String,
        @SerialName("start_time") val startTime: String,
        @SerialName("end_time") val endTime: String,
        val scores: WvwMatchWorldCount,
        val worlds: WvwMatchWorldCount,
        @SerialName("all_worlds") val allWorlds: WvwMatchAllWorlds,
        val deaths: WvwMatchWorldCount,
        val kills: WvwMatchWorldCount,
        @SerialName("victory_points") val victoryPoints: WvwMatchWorldCount,
        val maps: List<WvwMatchMap>,
        val skirmishes: List<WvwMatchSkirmish>
)