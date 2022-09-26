package com.kryszak.gwatlin.api.characters.model.character.sab

/**
 * Data model for SAB zone progress
 */
data class SabZone(
    val id: Int,
    val mode: SabZoneMode,
    val world: Int,
    val zone: Int
)