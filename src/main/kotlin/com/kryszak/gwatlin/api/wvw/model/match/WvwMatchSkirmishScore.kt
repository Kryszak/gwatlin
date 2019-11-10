package com.kryszak.gwatlin.api.wvw.model.match

/**
 * Data model for wvw match skirmish score property
 */
data class WvwMatchSkirmishScore(
        val type: String,
        val scores: WvwMatchWorldCount
)
