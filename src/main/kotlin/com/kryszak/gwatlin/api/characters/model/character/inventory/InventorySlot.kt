package com.kryszak.gwatlin.api.characters.model.character.inventory

import com.google.gson.annotations.SerializedName
import com.kryszak.gwatlin.api.characters.model.character.ItemStats
import com.kryszak.gwatlin.api.shared.ItemBinding

data class InventorySlot(
    val id: Int,
    val count: Int,
    val charges: Int?,
    val infusions: List<Int>?,
    val upgrades: List<Int>?,
    @SerializedName("upgrade_slot_indices")
    val upgradeSlotIndices: List<Int>?,
    val skin: Int?,
    val stats: ItemStats?,
    val dyes: List<Int>?,
    val binding: ItemBinding?,
    @SerializedName("bound_to")
    val boundTo: String?
)