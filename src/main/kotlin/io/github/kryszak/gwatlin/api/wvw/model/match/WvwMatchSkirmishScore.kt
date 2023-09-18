package io.github.kryszak.gwatlin.api.wvw.model.match

import kotlinx.serialization.Serializable

/**
 * Data model for wvw match skirmish score property
 */
@Serializable
data class WvwMatchSkirmishScore(
        val type: String,
        val scores: WvwMatchWorldCount
)
