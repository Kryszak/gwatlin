package com.kryszak.gwatlin.api.characters.model.character.equipment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for equipment tabs
 */
@Serializable
data class EquipmentTab(
    val tab: Int,
    val name: String,
    @SerialName("is_active")
    val isActive: Boolean,
    val equipment: List<EquipmentItem>,
    @SerialName("equipment_pvp")
    val equipmentPvp: EquipmentPvp
)