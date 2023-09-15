package com.kryszak.gwatlin.api.characters.model.character.sab

import kotlinx.serialization.Serializable

/**
 * Data model for infos about SAB progress and unlocks
 */
@Serializable
data class CharacterSAB(
    val zones: List<SabZone>,
    val unlocks: List<SabUnlock>,
    val songs: List<SabSong>
)
