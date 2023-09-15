package com.kryszak.gwatlin.api.pvp.model.game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for pvp game object
 */
@Serializable
data class PvpGame(
        val id: String,
        @SerialName("map_id") val mapId: Int,
        val started: String,
        val ended: String,
        val result: String,
        val team: String,
        val profession: String? = null,
        val scores: PvpGameScore,
        @SerialName("rating_type") val ratingType: String,
        @SerialName("rating_change") val ratingChange: Int? = null,
        val season: String? = null
)
