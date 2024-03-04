package io.github.kryszak.gwatlin.api.gamemechanics.model.skill

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Skill slot values
 */
@Serializable
enum class SkillSlot {
    @SerialName("Downed_[1-4]")
    DOWNED1_4,
    @SerialName("Pet")
    PET,
    @SerialName("Profession_[1-5]")
    PROFESSION1_5,
    @SerialName("Profession_2")
    PROFESSION2,
    @SerialName("Utility")
    UTILITY,
    @SerialName("Weapon_[1-5]")
    WEAPON1_5,
    @SerialName("Elite")
    ELITE,
}
