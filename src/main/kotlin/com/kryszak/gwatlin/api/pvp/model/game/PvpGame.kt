package com.kryszak.gwatlin.api.pvp.model.game

import com.google.gson.annotations.SerializedName

/**
 * Data model for pvp game object
 */
data class PvpGame(
        val id: String,
        @SerializedName("map_id") val mapId: Int,
        val started: String,
        val ended: String,
        val result: String,
        val team: String,
        val profession: String?,
        val scores: PvpGameScore,
        @SerializedName("rating_type") val ratingType: String,
        @SerializedName("rating_change") val ratingChange: String,
        val season: String?
)
