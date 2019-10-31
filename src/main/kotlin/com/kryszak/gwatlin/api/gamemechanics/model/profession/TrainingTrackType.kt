package com.kryszak.gwatlin.api.gamemechanics.model.profession

import com.google.gson.annotations.SerializedName

/**
 * Training track type values
 */
enum class TrainingTrackType {
    @SerializedName("Trait")
    TRAIT,
    @SerializedName("Skill")
    SKILL
}
