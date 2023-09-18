package io.github.kryszak.gwatlin.api.gamemechanics.model.mastery

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for mastery level object
 */
@Serializable
data class MasteryLevel(
        val name: String,
        val description: String,
        val instruction: String,
        val icon: String,
        @SerialName("point_cost") val pointCost: Int,
        @SerialName("exp_cost") val expCost: Int
)

