package com.kryszak.gwatlin.api.gamemechanics.model.mastery

import com.google.gson.annotations.SerializedName

/**
 * Data model for mastery level object
 */
data class MasteryLevel(
        val name: String,
        val description: String,
        val instruction: String,
        val icon: String,
        @SerializedName("point_cost") val pointCost: Int,
        @SerializedName("exp_cost") val expCost: Int
)

