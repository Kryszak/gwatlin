package io.github.kryszak.gwatlin.api.items.model.stats

import kotlinx.serialization.Serializable

/**
 * Data model for item stats object
 */
@Serializable
data class ItemStats(
        val id: Int,
        val name: String,
        val attributes: List<StatAttribute> = listOf(),
)
