package com.kryszak.gwatlin.api.characters.model.character.equipment

import com.kryszak.gwatlin.api.characters.model.character.ItemStats
import com.kryszak.gwatlin.api.shared.ItemSlot
import com.kryszak.gwatlin.api.shared.ItemBinding
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for equipment items
 */
@Serializable
data class EquipmentItem(
    val id: Int,
    val slot: ItemSlot,
    val binding: ItemBinding,
    @SerialName("bound_to")
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