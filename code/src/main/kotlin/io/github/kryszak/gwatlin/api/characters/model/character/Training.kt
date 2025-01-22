package io.github.kryszak.gwatlin.api.characters.model.character

import kotlinx.serialization.Serializable

/**
 * Data model for training infos
 */
@Serializable
data class Training(
    val id: Int,
    val spent: Int,
    val done: Boolean
)