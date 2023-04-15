package com.kryszak.gwatlin.api.characters.model.character.inventory

import com.kryszak.gwatlin.api.characters.model.character.ItemStats
import com.kryszak.gwatlin.api.shared.ItemBinding
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for inventory slots
 */
@Serializable
data class InventorySlot(
    val id: Int,
    val count: Int,
    val charges: Int?,
    val infusions: List<Int>?,
    val upgrades: List<Int>?,
    @SerialName("upgrade_slot_indices")
    val upgradeSlotIndices: List<Int>?,
    val skin: Int?,
    val stats: ItemStats?,
    val dyes: List<Int>?,
    val binding: ItemBinding?,
    @SerialName("bound_to")
    val boundTo: String?
)