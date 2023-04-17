package com.kryszak.gwatlin.api.gamemechanics.model.profession

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Training category values
 */
@Serializable
enum class TrainingCategory {
    @SerialName("Skills")
    SKILLS,
    @SerialName("Specializations")
    SPECIALIZATIONS,
    @SerialName("EliteSpecializations")
    ELITE_SPECIALIZATIONS
}
