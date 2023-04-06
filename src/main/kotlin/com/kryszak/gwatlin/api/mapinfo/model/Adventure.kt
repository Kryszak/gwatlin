package com.kryszak.gwatlin.api.mapinfo.model

/**
 * Data model for Adventures
 */
data class Adventure(
    val id: String,
    val coord: Coordinates,
    val name: String,
    val description: String
)
