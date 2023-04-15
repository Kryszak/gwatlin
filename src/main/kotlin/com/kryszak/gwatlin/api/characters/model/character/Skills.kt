package com.kryszak.gwatlin.api.characters.model.character

import kotlinx.serialization.Serializable

/**
 * Data model for skill sets
 */
@Serializable
data class Skills @JvmOverloads constructor(
    val heal: Int,
    val utilities: Set<Int>,
    val elite: Int,
    val legends: Set<String>? = null
)
