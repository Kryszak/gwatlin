package io.github.kryszak.gwatlin.api.gamemechanics.model

import kotlinx.serialization.Serializable

/**
 * Data model for currency object
 */
@Serializable
data class Currency(
        val id: Int,
        val name: String,
        val description: String,
        val icon: String,
        val order: Int
)
