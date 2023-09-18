package io.github.kryszak.gwatlin.api.characters.model.character.sab

import kotlinx.serialization.Serializable

/**
 * Data model for SAB unlocks
 */
@Serializable
data class SabUnlock(
    val id: Int,
    val name: String? = null
)