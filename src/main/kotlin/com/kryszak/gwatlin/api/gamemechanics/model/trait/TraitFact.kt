package com.kryszak.gwatlin.api.gamemechanics.model.trait

import kotlinx.serialization.Serializable

/**
 * Data model for trait fact property
 */
@Serializable
data class TraitFact(
        val text: String?,
        val icon: String?,
        val type: String
)
