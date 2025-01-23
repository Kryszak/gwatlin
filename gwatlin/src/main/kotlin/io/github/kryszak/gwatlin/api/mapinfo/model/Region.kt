package io.github.kryszak.gwatlin.api.mapinfo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.Map

/**
 * Data model for regions. Regions are part of a [io.github.kryszak.gwatlin.api.mapinfo.model.Floor]
 */
@Serializable
data class Region(
    val id: Int,
    val name: String,
    @SerialName("label_coord")
    val labelCoord: Coordinates,
    @SerialName("continent_rect")
    val continentRect: Rectangle,
    val maps: Map<Int, ContinentMap> = mapOf(),
)
