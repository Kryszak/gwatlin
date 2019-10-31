package com.kryszak.gwatlin.api.items.model.finisher

import com.google.gson.annotations.SerializedName

/**
 * Data model for finisher object
 */
data class Finisher(
        val id: Int,
        @SerializedName("unlock_details") val unlockDetails: String,
        @SerializedName("unlock_items") val unlockItems: List<Int>,
        val order: Int,
        val icon: String,
        val name: String
)
