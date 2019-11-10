package com.kryszak.gwatlin.api.wvw.model.match

import com.google.gson.annotations.SerializedName

/**
 * Data model for wvw match object
 */
data class WvwMatch(
        val id: String,
        @SerializedName("start_time") val startTime: String,
        @SerializedName("end_time") val endTime: String,
        val scores: WvwMatchWorldCount,
        val worlds: WvwMatchWorldCount,
        @SerializedName("all_worlds") val allWorlds: WvwMatchAllWorlds,
        val deaths: WvwMatchWorldCount,
        val kills: WvwMatchWorldCount,
        @SerializedName("victory_points") val victoryPoints: WvwMatchWorldCount,
        val maps: List<WvwMatchMap>,
        val skirmishes: List<WvwMatchSkirmish>
)