package io.github.kryszak.gwatlin.api.wvw.model.objective

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for wvw objective object
 */
@Serializable
data class WvwObjective(
        @SerialName("chat_link")
        val chatLink: String,
        val coord: List<Double> = listOf(),
        val id: String,
        @SerialName("label_coord")
        val labelCoord: List<Double> = listOf(),
        @SerialName("map_id")
        val mapId: Int,
        @SerialName("map_type")
        val mapType: String,
        val marker: String,
        val name: String,
        @SerialName("sector_id")
        val sectorId: Int,
        val type: String,
        @SerialName("upgrade_id")
        val upgradeId: Int
)
