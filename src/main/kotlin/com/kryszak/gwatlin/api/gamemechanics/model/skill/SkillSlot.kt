package com.kryszak.gwatlin.api.gamemechanics.model.skill

import com.google.gson.annotations.SerializedName

/**
 * Skill slot values
 */
enum class SkillSlot {
    @SerializedName("Downed_[1-4]")
    DOWNED1_4,
    @SerializedName("Pet")
    PET,
    @SerializedName("Profession_[1-5]")
    PROFESSION1_5,
    @SerializedName("Profession_2")
    PROFESSION2,
    @SerializedName("Utility")
    UTILITY,
    @SerializedName("Weapon_[1-5]")
    WEAPON1_5
}
