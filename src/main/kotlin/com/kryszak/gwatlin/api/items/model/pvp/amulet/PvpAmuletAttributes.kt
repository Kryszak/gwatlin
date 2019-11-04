package com.kryszak.gwatlin.api.items.model.pvp.amulet

import com.google.gson.annotations.SerializedName

/**
 * Data model for pvp amulet attributes property
 */
data class PvpAmuletAttributes(
        @SerializedName("AgonyResistance") val agonyResistance: Double?,
        @SerializedName("BoonDuration") val boonDuration: Double?,
        @SerializedName("ConditionDamage") val conditionDamage: Double?,
        @SerializedName("ConditionDruration") val conditionDuration: Double?,
        @SerializedName("CritDamage") val critDamage: Double?,
        @SerializedName("Healing") val healing: Double?,
        @SerializedName("Power") val power: Double?,
        @SerializedName("Precision") val precision: Double?,
        @SerializedName("Toughness") val toughness: Double?,
        @SerializedName("Vitality") val vitality: Double?
)
