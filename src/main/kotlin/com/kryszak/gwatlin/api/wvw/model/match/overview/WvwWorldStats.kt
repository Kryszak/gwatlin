package com.kryszak.gwatlin.api.wvw.model.match.overview

import com.kryszak.gwatlin.api.wvw.model.match.WvwMatchMap
import com.kryszak.gwatlin.api.wvw.model.match.WvwMatchWorldCount

/**
 * Data model for stats sub-endpoint
 */
data class WvwWorldStats(
        val id: String,
        val deaths: WvwMatchWorldCount,
        val kills: WvwMatchWorldCount,
        val maps: List<WvwMatchMap>
)
