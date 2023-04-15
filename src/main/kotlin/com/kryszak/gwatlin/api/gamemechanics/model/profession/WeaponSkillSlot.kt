package com.kryszak.gwatlin.api.gamemechanics.model.profession

import com.google.gson.annotations.SerializedName

/**
 * Weapon skill type values
 */
enum class WeaponSkillSlot {
    @SerializedName("Profession_1")
    PROFESSION_1,
    @SerializedName("Utility")
    UTILITY,
    @SerializedName("Heal")
    HEAL,
    @SerializedName("Elite")
    ELITE
}
