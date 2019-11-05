package com.kryszak.gwatlin.api.miscellaneous.model

import com.google.gson.annotations.SerializedName

/**
 * Data model for mini object
 */
data class Mini(
        val id: Int,
        val name: String,
        val icon: String,
        val order: Int,
        @SerializedName("item_id") val itemId: Int
)
