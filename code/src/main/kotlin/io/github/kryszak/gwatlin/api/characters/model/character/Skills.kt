package io.github.kryszak.gwatlin.api.characters.model.character

import kotlinx.serialization.Serializable

/**
 * Data model for skill sets
 */
@Serializable
data class Skills @JvmOverloads constructor(
    val heal: Int? = null,
    val utilities: Set<Int?> = setOf(),
    val elite: Int? = null,
    val legends: Set<String>? = null
)
