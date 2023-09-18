package io.github.kryszak.gwatlin.api.gamemechanics.model.skill

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Skill type values
 */
@Serializable
enum class SkillType {
    @SerialName("Bundle")
    BUNDLE,
    @SerialName("Elite")
    ELITE,
    @SerialName("Heal")
    HEAL,
    @SerialName("Profession")
    PROFESSION,
    @SerialName("Utility")
    UTILITY,
    @SerialName("Weapon")
    WEAPON
}
