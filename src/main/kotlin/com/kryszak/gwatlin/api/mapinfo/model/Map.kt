package com.kryszak.gwatlin.api.mapinfo.model

import com.google.gson.annotations.SerializedName

/**
 * Data model for map info returned by the /maps endpoint
 */
data class Map(
    val id: Int,
    val name: String,
    @SerializedName("min_level")
    val minLevel: Int,
    @SerializedName("max_level")
    val maxLevel: Int,
    @SerializedName("default_floor")
    val defaultFloor: Int,
    val type: MapType,
    val floors: List<Int>,
    @SerializedName("region_id")
    val regionId: Int,
    @SerializedName("region_name")
    val regionName: String?,
    @SerializedName("continent_id")
    val continentId: Int,
    @SerializedName("continent_name")
    val continentName: String?,
    @SerializedName("map_rect")
    val mapRect: Rectangle,
    @SerializedName("continent_rect")
    val continentRect: Rectangle
)
