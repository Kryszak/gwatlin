package com.kryszak.gwatlin.api.gamemechanics.model.profession

import com.google.gson.annotations.SerializedName

/**
 * Training category values
 */
enum class TrainingCategory {
    @SerializedName("Skills")
    SKILLS,
    @SerializedName("5821")
    SPECIALIZATIONS,
    @SerializedName("EliteSpecializations")
    ELITE_SPECIALIZATIONS
}
