package com.kryszak.gwatlin.api.guild.model.upgrade

import com.google.gson.annotations.SerializedName

/**
 * Data model for cost property of guild upgrade object
 */
data class UpgradeCost(
        val type: String,
        val name: String,
        val count: Int,
        @SerializedName("item_id") val itemId: Int?
)
