package com.kryszak.gwatlin.api.mapinfo.model

/**
 * Data model mastery points.
 * @param region defines the region the mastery point belongs to,
 * i.e. "Tyria" for core Tyria or "Maguuma" for Heart of Thorns
 */
data class MasteryPoint(
    val id: Int,
    val coord: Coordinates,
    val region: String
)
