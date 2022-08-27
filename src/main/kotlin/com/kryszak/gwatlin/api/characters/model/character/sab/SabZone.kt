package com.kryszak.gwatlin.api.characters.model.character.sab

data class SabZone(
    val id: Int,
    val mode: SabZoneMode,
    val world: Int,
    val zone: Int
)