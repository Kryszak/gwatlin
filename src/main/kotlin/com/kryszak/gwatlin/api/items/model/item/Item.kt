package com.kryszak.gwatlin.api.items.model.item

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for item class
 */
@Serializable
data class Item(
    val id: Int,
    @SerialName("chat_link") val chatLink: String,
    val name: String,
    val icon: String,
    val description: String,
    val type: ItemType,
    val rarity: ItemRarity,
    val level: Int,
    @SerialName("vendor_value") val vendorValue: Int,
    @SerialName("default_skin") val defaultSkin: Int?,
    val flags: List<String>,
    @SerialName("game_types") val gameTypes: List<String>,
    val restrictions: List<String>,
    val details: Map<String, Any>
)
