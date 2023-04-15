package com.kryszak.gwatlin.api.gamemechanics.model.profession

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Weapon skill type values
 */
@Serializable
enum class WeaponSkillSlot {
    @SerialName("Profession_1")
    PROFESSION_1,
    @SerialName("Utility")
    UTILITY,
    @SerialName("Heal")
    HEAL,
    @SerialName("Elite")
    ELITE
}
