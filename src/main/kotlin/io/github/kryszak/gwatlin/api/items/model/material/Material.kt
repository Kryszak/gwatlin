package io.github.kryszak.gwatlin.api.items.model.material

import kotlinx.serialization.Serializable

/**
 * Data model for material object
 */
@Serializable
data class Material(
        val id: Int,
        val name: String,
        val items: List<Int> = listOf(),
        val order: Int? = null
)
