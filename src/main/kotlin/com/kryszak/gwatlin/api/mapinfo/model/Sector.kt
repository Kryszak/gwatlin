package com.kryszak.gwatlin.api.mapinfo.model

import com.google.gson.annotations.SerializedName

data class Sector(
    val id: Int,
    val name: String,
    val level: Int,
    val coord: Coordinates,
    val bounds: List<Coordinates>,
    @SerializedName("chat_link")
    val chatLink: String
)
