package io.github.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.Serializable

/**
 * Data model for buff prefix object
 */
@Serializable
data class BuffPrefix(
    val text: String,
    val icon: String,
    val status: String? = null,
    val description: String? = null,
)
