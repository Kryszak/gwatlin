package com.kryszak.gwatlin.api.characters.model.character

/**
 * Data model for item stats
 */
data class ItemStats(
    val id: Int,
    val attributes: Map<String, Int>
)
