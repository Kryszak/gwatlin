package com.kryszak.gwatlin.api.pvp.model.season

import kotlinx.serialization.Serializable

/**
 * Data model for pvp season division tier property
 */
@Serializable
data class Tier(
        val points: Int
)