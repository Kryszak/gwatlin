package com.kryszak.gwatlin.api.gamemechanics.model.outfit

import com.google.gson.annotations.SerializedName

/**
 * Data model for outfit object
 */
data class Outfit(
        val id: Int,
        val name: String,
        val icon: String,
        @SerializedName(value = "unlock_items") val unlockItems: List<Int>
)
