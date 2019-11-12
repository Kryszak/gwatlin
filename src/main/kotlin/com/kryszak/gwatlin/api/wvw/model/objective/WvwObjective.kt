package com.kryszak.gwatlin.api.wvw.model.objective

import com.google.gson.annotations.SerializedName

/**
 * Data model for wvw objective object
 */
data class WvwObjective(
        @SerializedName("chat_link") val chatLink: String,
        val coord: List<Double>,
        val id: String,
        @SerializedName("label_coord") val labelCoord: List<Double>,
        @SerializedName("map_id") val mapId: Int,
        @SerializedName("map_type") val mapType: String,
        val marker: String,
        val name: String,
        @SerializedName("sector_id") val sectorId: Int,
        val type: String,
        @SerializedName("upgrade_id") val upgradeId: Int
)
