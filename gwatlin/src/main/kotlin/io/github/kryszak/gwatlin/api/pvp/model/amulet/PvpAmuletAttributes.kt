package io.github.kryszak.gwatlin.api.pvp.model.amulet

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for pvp amulet attributes property
 */
@Serializable
data class PvpAmuletAttributes(
        @SerialName("ConditionDamage") val conditionDamage: Double? = null,
        @SerialName("CritDamage") val critDamage: Double? = null,
        @SerialName("Healing") val healing: Double? = null,
        @SerialName("Power") val power: Double? = null,
        @SerialName("Precision") val precision: Double? = null,
        @SerialName("Toughness") val toughness: Double? = null,
        @SerialName("Vitality") val vitality: Double? = null
)
