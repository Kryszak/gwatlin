package com.kryszak.gwatlin.api.mapinfo.model

import com.google.gson.annotations.SerializedName

/**
 * Data model for Continents
 */
data class Continent(
    val id: Int,
    val name: String,
    @SerializedName("continent_dims")
    val continentDims: Dimensions,
    @SerializedName("min_zoom")
    val minZoom: Int,
    @SerializedName("max_zoom")
    val maxZoom: Int,
    val floors: List<Int>
)
