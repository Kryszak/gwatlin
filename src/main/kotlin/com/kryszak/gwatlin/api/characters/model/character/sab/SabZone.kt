package com.kryszak.gwatlin.api.characters.model.character.sab

import kotlinx.serialization.Serializable

/**
 * Data model for SAB zone progress
 */
@Serializable
data class SabZone(
    val id: Int,
    val mode: SabZoneMode,
    val world: Int,
    val zone: Int
)