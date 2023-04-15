package com.kryszak.gwatlin.api.gamemechanics.model.profession

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Training track type values
 */
@Serializable
enum class TrainingTrackType {
    @SerialName("Trait")
    TRAIT,
    @SerialName("Skill")
    SKILL
}
