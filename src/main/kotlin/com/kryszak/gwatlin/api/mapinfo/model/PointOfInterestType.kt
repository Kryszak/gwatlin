package com.kryszak.gwatlin.api.mapinfo.model

import com.google.gson.annotations.SerializedName

/**
 * Enumeration for the possible types of a point of interest
 */
enum class PointOfInterestType {
    @SerializedName("landmark")
    LANDMARK,
    @SerializedName("waypoint")
    WAYPOINT,
    @SerializedName("vista")
    VISTA,
    @SerializedName("unlock")
    UNLOCK
}