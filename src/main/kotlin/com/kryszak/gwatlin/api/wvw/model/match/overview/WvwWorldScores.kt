package com.kryszak.gwatlin.api.wvw.model.match.overview

import com.google.gson.annotations.SerializedName
import com.kryszak.gwatlin.api.wvw.model.match.WvwMatchMap
import com.kryszak.gwatlin.api.wvw.model.match.WvwMatchSkirmish
import com.kryszak.gwatlin.api.wvw.model.match.WvwMatchWorldCount

/**
 * Data model for scores sub-endpoint
 */
data class WvwWorldScores(
        val id: String,
        val maps: List<WvwMatchMap>,
        val scores: WvwMatchWorldCount,
        val skirmishes: List<WvwMatchSkirmish>,
        @SerializedName("victory_points") val victoryPoints: WvwMatchWorldCount
)
