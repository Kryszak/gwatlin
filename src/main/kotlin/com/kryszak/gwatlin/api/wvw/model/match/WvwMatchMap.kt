package com.kryszak.gwatlin.api.wvw.model.match

/**
 * Data model for wvw match map property
 */
data class WvwMatchMap(
        val id: Int,
        val type: String,
        val scores: WvwMatchWorldCount,
        val kills: WvwMatchWorldCount,
        val deaths: WvwMatchWorldCount,
        val objectives: List<WvwMatchMapObjective>,
        val bonuses: List<WvwMatchMapBonus>
)
