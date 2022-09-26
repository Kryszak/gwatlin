package com.kryszak.gwatlin.api.mapinfo.model

import com.google.gson.annotations.SerializedName

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