package com.kryszak.gwatlin.api.gamemechanics.model.skill

import com.google.gson.annotations.SerializedName

/**
 * Data model for traited fact skill property
 */
data class TraitedFact(
        @SerializedName("requires_trait") val requiresTrait: Int,
        val overrides: Int
)
