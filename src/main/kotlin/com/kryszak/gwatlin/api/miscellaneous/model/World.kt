package com.kryszak.gwatlin.api.miscellaneous.model

import kotlinx.serialization.Serializable

/**
 * Data model for world object
 */
@Serializable
data class World(
        val id: Int,
        val name: String,
        val population: String
)
