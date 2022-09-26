package com.kryszak.gwatlin.api.mapinfo.model

import com.google.gson.annotations.SerializedName
import kotlin.collections.Map

data class Region(
    val name: String,
    @SerializedName("label_coord")
    val labelCoord: Coordinates,
    @SerializedName("continent_rect")
    val continentRect: Rectangle,
    val maps: Map<Int, ContinentMap>
)
