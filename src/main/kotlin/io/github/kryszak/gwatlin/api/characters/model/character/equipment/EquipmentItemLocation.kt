package io.github.kryszak.gwatlin.api.characters.model.character.equipment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Enum for possible equipment item locations
 */
@Serializable
enum class EquipmentItemLocation {
    @SerialName("Equipped")
    EQUIPPED,
    @SerialName("Armory")
    ARMORY,
    @SerialName("LegendaryArmory")
    LEGENDARY_ARMORY,
    @SerialName("EquippedFromLegendaryArmory")
    EQUIPPED_FROM_LEGENDARY_ARMORY
}
