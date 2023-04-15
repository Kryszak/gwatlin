package com.kryszak.gwatlin.api.pvp.model.game

import kotlinx.serialization.Serializable

/**
 * Data model for pvp score object
 */
@Serializable
data class PvpGameScore(
        val red: Int,
        val blue: Int
)
