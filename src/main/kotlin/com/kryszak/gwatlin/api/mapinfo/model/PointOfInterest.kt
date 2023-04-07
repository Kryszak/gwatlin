package com.kryszak.gwatlin.api.mapinfo.model

import com.google.gson.annotations.SerializedName

/**
 * Data model for points of interest, aka "POI"
 */
data class PointOfInterest(
    val id: Int,
    val name: String,
    val type: PointOfInterestType,
    val floor: Int,
    val coord: Coordinates,
    @SerializedName("chat_link")
    val chatLink: String,
    val icon: String?
)
