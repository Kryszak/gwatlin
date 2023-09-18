package io.github.kryszak.gwatlin.api.characters.model.character

import kotlinx.serialization.Serializable

/**
 * Data model for crafting infos
 */
@Serializable
data class Crafting(
    val discipline: String,
    val rating: Int,
    val active: Boolean
)
