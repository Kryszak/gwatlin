package com.kryszak.gwatlin.api.characters.model.character.equipment

data class EquipmentPvp(
    val amulet: Int,
    val rune: Int,
    val sigils: List<Int?>
)