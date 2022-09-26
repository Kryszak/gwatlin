package com.kryszak.gwatlin.api.characters.model.character.sab

/**
 * Data model for infos about SAB progress and unlocks
 */
data class CharacterSAB(
    val zones: List<SabZone>,
    val unlocks: List<SabUnlock>,
    val songs: List<SabSong>
)
