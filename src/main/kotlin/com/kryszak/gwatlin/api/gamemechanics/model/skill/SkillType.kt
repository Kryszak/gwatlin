package com.kryszak.gwatlin.api.gamemechanics.model.skill

import com.google.gson.annotations.SerializedName

/**
 * Skill type values
 */
enum class SkillType {
    @SerializedName("Bundle")
    BUNDLE,
    @SerializedName("Elite")
    ELITE,
    @SerializedName("Heal")
    HEAL,
    @SerializedName("Profession")
    PROFESSION,
    @SerializedName("Utility")
    UTILITY,
    @SerializedName("Weapon")
    WEAPON
}
