package com.kryszak.gwatlin.api.characters.model.character.equipment

import com.google.gson.annotations.SerializedName
import com.kryszak.gwatlin.api.items.model.stats.ItemStats

data class EquipmentItem(
    val id: Int,
    val slot: EquipmentItemSlot,
    val binding: EquipmentItemBinding,
    @SerializedName("bound_to")
    val boundTo: String?,
    val infusions: List<Int>?,
    val location: EquipmentItemLocation?,
    val skin: Int?,
    val upgrades: List<Int>?,
    val stats: EquipmentItemStats?,
    val dyes: List<Int>?,
    val charges: Int?,
    val tabs: Set<Int>?
)