package com.kryszak.gwatlin.api.wvw.model.match

import kotlinx.serialization.Serializable

/**
 * Data model for wvw match all worlds property
 */
@Serializable
data class WvwMatchAllWorlds(
        val red: List<Int>,
        val blue: List<Int>,
        val green: List<Int>
)
