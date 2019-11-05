package com.kryszak.gwatlin.api.miscellaneous.model

import com.google.gson.annotations.SerializedName

/**
 * Data model for novelty object
 */
data class Novelty(
        val id: Int,
        val name: String,
        val description: String,
        val icon: String,
        val slot: String,
        @SerializedName("unlock_item") val unlockItem: List<Int>?
)
