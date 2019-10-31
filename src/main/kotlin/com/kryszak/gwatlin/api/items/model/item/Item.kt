package com.kryszak.gwatlin.api.items.model.item

import com.google.gson.annotations.SerializedName

/**
 * Data model for item class
 */
data class Item(
    val id: Int,
    @SerializedName("chat_link") val chatLink: String,
    val name: String,
    val icon: String,
    val description: String,
    val type: ItemType,
    val rarity: ItemRarity,
    val level: Int,
    @SerializedName("vendor_value") val vendorValue: Int,
    @SerializedName("default_skin") val defaultSkin: Int?,
    val flags: List<String>,
    @SerializedName("game_types") val gameTypes: List<String>,
    val restrictions: List<String>
)
