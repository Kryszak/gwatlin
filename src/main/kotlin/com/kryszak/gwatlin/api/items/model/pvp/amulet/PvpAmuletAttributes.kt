package com.kryszak.gwatlin.api.items.model.pvp.amulet

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for pvp amulet attributes property
 */
@Serializable
data class PvpAmuletAttributes(
        @SerialName("AgonyResistance") val agonyResistance: Double?,
        @SerialName("BoonDuration") val boonDuration: Double?,
        @SerialName("ConditionDamage") val conditionDamage: Double?,
        @SerialName("ConditionDruration") val conditionDuration: Double?,
        @SerialName("CritDamage") val critDamage: Double?,
        @SerialName("Healing") val healing: Double?,
        @SerialName("Power") val power: Double?,
        @SerialName("Precision") val precision: Double?,
        @SerialName("Toughness") val toughness: Double?,
        @SerialName("Vitality") val vitality: Double?
)
