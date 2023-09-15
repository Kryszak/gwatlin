package com.kryszak.gwatlin.api.mapinfo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonNames

/**
 * Enumeration for the possible types of a point of interest
 */
enum class PointOfInterestType {
    @SerialName("landmark")
    LANDMARK,
    @SerialName("waypoint")
    WAYPOINT,
    @SerialName("vista")
    VISTA,
    @SerialName("unlock")
    UNLOCK
}