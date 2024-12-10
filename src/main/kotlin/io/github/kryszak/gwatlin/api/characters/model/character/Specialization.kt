package io.github.kryszak.gwatlin.api.characters.model.character

import kotlinx.serialization.Serializable

/**
 * Data model for specialization infos
 */
@Serializable
data class Specialization(
    val id: Int? = null,
    val traits: Set<Int?> = setOf(),
)