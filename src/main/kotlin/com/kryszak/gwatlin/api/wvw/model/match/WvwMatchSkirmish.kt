package com.kryszak.gwatlin.api.wvw.model.match

import com.google.gson.annotations.SerializedName

/**
 * Data model for wvw match skirmish property
 */
data class WvwMatchSkirmish(
        val id: Int,
        val scores: WvwMatchWorldCount,
        @SerializedName("map_scores") val mapScores: List<WvwMatchSkirmishScore>
)
