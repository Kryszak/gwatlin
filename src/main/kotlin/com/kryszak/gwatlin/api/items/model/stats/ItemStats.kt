package com.kryszak.gwatlin.api.items.model.stats

/**
 * Data model for item stats object
 */
data class ItemStats(
        val id: Int,
        val name: String,
        val attributes: List<StatAttribute>
)
