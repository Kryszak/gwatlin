package com.kryszak.gwatlin.api.gamemechanics.model.skill

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for traited fact skill property
 */
@Serializable
data class TraitedFact(
        @SerialName("requires_trait") val requiresTrait: Int,
        val overrides: Int
)
