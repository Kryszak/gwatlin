package com.kryszak.gwatlin.api.characters.model.character

/**
 * Data model for crafting infos
 */
data class Crafting(
    val discipline: String,
    val rating: Int,
    val active: Boolean
)
