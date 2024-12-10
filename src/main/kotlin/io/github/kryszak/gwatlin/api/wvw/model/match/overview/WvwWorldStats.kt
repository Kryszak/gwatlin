package io.github.kryszak.gwatlin.api.wvw.model.match.overview

import io.github.kryszak.gwatlin.api.wvw.model.match.WvwMatchMap
import io.github.kryszak.gwatlin.api.wvw.model.match.WvwMatchWorldCount
import kotlinx.serialization.Serializable

/**
 * Data model for stats sub-endpoint
 */
@Serializable
data class WvwWorldStats(
        val id: String,
        val deaths: WvwMatchWorldCount,
        val kills: WvwMatchWorldCount,
        val maps: List<WvwMatchMap> = listOf()
)
