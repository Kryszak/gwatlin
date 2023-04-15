package com.kryszak.gwatlin.api.characters.model.character.equipment

import kotlinx.serialization.Serializable

/**
 * Data model for pvp equipment
 */
@Serializable
data class EquipmentPvp(
    val amulet: Int,
    val rune: Int,
    val sigils: List<Int?>
)