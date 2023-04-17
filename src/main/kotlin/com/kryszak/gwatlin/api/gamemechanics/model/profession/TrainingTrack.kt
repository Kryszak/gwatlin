package com.kryszak.gwatlin.api.gamemechanics.model.profession

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model of track property of training object
 */
@Serializable
data class TrainingTrack(
        val cost: Int,
        val type: TrainingTrackType,
        @SerialName("skill_id") val skillId: Int? = null,
        @SerialName("trait_id") val traitId: Int? = null
)
