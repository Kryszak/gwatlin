package com.kryszak.gwatlin.api.characters.model.character

import kotlinx.serialization.Serializable

/**
 * Data model for specialization infos
 */
@Serializable
data class Specialization(
    val id: Int,
    val traits: Set<Int>
)