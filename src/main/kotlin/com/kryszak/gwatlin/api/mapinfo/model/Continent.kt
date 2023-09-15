package com.kryszak.gwatlin.api.mapinfo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for Continents
 */
@Serializable
data class Continent(
    val id: Int,
    val name: String,
    @SerialName("continent_dims")
    val continentDims: Dimensions,
    @SerialName("min_zoom")
    val minZoom: Int,
    @SerialName("max_zoom")
    val maxZoom: Int,
    val floors: List<Int>
)
