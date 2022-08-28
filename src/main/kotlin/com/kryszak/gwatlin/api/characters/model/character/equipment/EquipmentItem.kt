package com.kryszak.gwatlin.api.characters.model.character.equipment

import com.google.gson.annotations.SerializedName
import com.kryszak.gwatlin.api.characters.model.character.ItemStats
import com.kryszak.gwatlin.api.shared.ItemSlot
import com.kryszak.gwatlin.api.shared.ItemBinding

data class EquipmentItem(
    val id: Int,
    val slot: ItemSlot,
    val binding: ItemBinding,
    @SerializedName("bound_to")
    val boundTo: String?,
    val infusions: List<Int>?,
    val location: EquipmentItemLocation?,
    val skin: Int?,
    val upgrades: List<Int>?,
    val stats: ItemStats?,
    val dyes: List<Int>?,
    val charges: Int?,
    val tabs: Set<Int>?
)