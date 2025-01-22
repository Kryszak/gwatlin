package io.github.kryszak.gwatlin.api.wvw.model.match

import kotlinx.serialization.Serializable

/**
 * Data model for wvw match map property
 */
@Serializable
data class WvwMatchMap(
        val id: Int,
        val type: String,
        val scores: WvwMatchWorldCount? = null,
        val kills: WvwMatchWorldCount? = null,
        val deaths: WvwMatchWorldCount? = null,
        val objectives: List<WvwMatchMapObjective> = listOf(),
        val bonuses: List<WvwMatchMapBonus> = listOf()
)
