package io.github.kryszak.gwatlin.api.characters.model.character.equipment

import kotlinx.serialization.Serializable

/**
 * Data model for pvp equipment
 */
@Serializable
data class EquipmentPvp(
    val amulet: Int? = null,
    val rune: Int? = null,
    val sigils: List<Int?> = listOf()
)