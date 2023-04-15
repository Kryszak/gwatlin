package com.kryszak.gwatlin.api.mapinfo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for sectors. Regions are part of a [com.kryszak.gwatlin.api.mapinfo.model.ContinentMap]
 */
@Serializable
data class Sector(
    val id: Int,
    val name: String,
    val level: Int,
    val coord: Coordinates,
    val bounds: List<Coordinates>,
    @SerialName("chat_link")
    val chatLink: String
)
