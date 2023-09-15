package com.kryszak.gwatlin.api.mapinfo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for map info returned by the /maps endpoint
 */
@Serializable
data class Map(
    val id: Int,
    val name: String,
    @SerialName("min_level")
    val minLevel: Int,
    @SerialName("max_level")
    val maxLevel: Int,
    @SerialName("default_floor")
    val defaultFloor: Int,
    val type: MapType,
    val floors: List<Int>,
    @SerialName("region_id")
    val regionId: Int,
    @SerialName("region_name")
    val regionName: String?,
    @SerialName("continent_id")
    val continentId: Int,
    @SerialName("continent_name")
    val continentName: String? = null,
    @SerialName("map_rect")
    val mapRect: Rectangle,
    @SerialName("continent_rect")
    val continentRect: Rectangle
)
