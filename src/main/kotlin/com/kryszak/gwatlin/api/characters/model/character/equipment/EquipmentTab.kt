package com.kryszak.gwatlin.api.characters.model.character.equipment

import com.google.gson.annotations.SerializedName

/**
 * Data model for equipment tabs
 */
data class EquipmentTab(
    val tab: Int,
    val name: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    val equipment: List<EquipmentItem>,
    @SerializedName("equipment_pvp")
    val equipmentPvp: EquipmentPvp
)