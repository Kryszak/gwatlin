package com.kryszak.gwatlin.api.mapinfo.model

import com.google.gson.annotations.SerializedName

data class HeartTask(
    val id: Int,
    val objective: String,
    val level: Int,
    val coord: Coordinates,
    val bounds: List<Coordinates>,
    @SerializedName("chat_link")
    val chatLink: String
)
