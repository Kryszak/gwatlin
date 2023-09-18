package io.github.kryszak.gwatlin.api.characters.model.character

import kotlinx.serialization.Serializable

/**
 * Data model for item stats
 */
@Serializable
data class ItemStats(
    val id: Int,
    val attributes: Map<String, Int>
)
