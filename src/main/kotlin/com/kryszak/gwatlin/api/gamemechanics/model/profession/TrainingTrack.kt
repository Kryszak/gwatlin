package com.kryszak.gwatlin.api.gamemechanics.model.profession

import com.google.gson.annotations.SerializedName

/**
 * Data model of track property of training object
 */
data class TrainingTrack(
        val cost: Int,
        val type: TrainingTrackType,
        @SerializedName("skill_id") val skillId: Int?,
        @SerializedName("trait_it") val traitId: Int?
)
