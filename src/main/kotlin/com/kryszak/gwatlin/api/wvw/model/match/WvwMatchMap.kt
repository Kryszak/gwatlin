package com.kryszak.gwatlin.api.wvw.model.match

import kotlinx.serialization.Serializable

/**
 * Data model for wvw match map property
 */
@Serializable
data class WvwMatchMap(
        val id: Int,
        val type: String,
        val scores: WvwMatchWorldCount,
        val kills: WvwMatchWorldCount,
        val deaths: WvwMatchWorldCount,
        val objectives: List<WvwMatchMapObjective>,
        val bonuses: List<WvwMatchMapBonus>
)
