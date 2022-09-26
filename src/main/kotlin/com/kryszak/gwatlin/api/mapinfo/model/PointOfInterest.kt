package com.kryszak.gwatlin.api.mapinfo.model

import com.google.gson.annotations.SerializedName

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
