package com.kryszak.gwatlin.api.mapinfo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for points of interest, aka "POI"
 */
@Serializable
data class PointOfInterest(
    val id: Int,
    val name: String,
    val type: PointOfInterestType,
    val floor: Int,
    val coord: Coordinates,
    @SerialName("chat_link")
    val chatLink: String,
    val icon: String?
)
