package io.github.kryszak.gwatlin.api.mapinfo.model

import kotlinx.serialization.Serializable

/**
 * Data model for Adventures
 */
@Serializable
data class Adventure(
    val id: String,
    val coord: Coordinates,
    val name: String,
    val description: String
)
