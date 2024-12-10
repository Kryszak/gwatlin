package io.github.kryszak.gwatlin.api.wvw.model.match

import kotlinx.serialization.Serializable

/**
 * Data model for wvw match all worlds property
 */
@Serializable
data class WvwMatchAllWorlds(
        val red: List<Int> = listOf(),
        val blue: List<Int> = listOf(),
        val green: List<Int> = listOf(),
)
