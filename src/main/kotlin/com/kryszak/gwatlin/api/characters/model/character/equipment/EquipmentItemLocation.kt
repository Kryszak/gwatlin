package com.kryszak.gwatlin.api.characters.model.character.equipment

import com.google.gson.annotations.SerializedName

enum class EquipmentItemLocation {
    @SerializedName("Equipped")
    EQUIPPED,
    @SerializedName("Armory")
    ARMORY,
    @SerializedName("LegendaryArmory")
    LEGENDARY_ARMORY,
    @SerializedName("EquippedFromLegendaryArmory")
    EQUIPPED_FROM_LEGENDARY_ARMORY
}
