package com.kryszak.gwatlin.api.items.model.material

/**
 * Data model for material object
 */
data class Material(
        val id: Int,
        val name: String,
        val items: List<Int>,
        val order: Int?
)
