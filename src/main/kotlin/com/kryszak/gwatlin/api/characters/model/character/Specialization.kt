package com.kryszak.gwatlin.api.characters.model.character

/**
 * Data model for specialization infos
 */
data class Specialization(
    val id: Int,
    val traits: Set<Int>
)