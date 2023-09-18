package io.github.kryszak.gwatlin.api.mapinfo.model

import kotlinx.serialization.SerialName

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