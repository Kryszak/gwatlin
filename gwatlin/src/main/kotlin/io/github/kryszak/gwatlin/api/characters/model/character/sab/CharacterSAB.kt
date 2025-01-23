package io.github.kryszak.gwatlin.api.characters.model.character.sab

import kotlinx.serialization.Serializable

/**
 * Data model for infos about SAB progress and unlocks
 */
@Serializable
data class CharacterSAB(
    val zones: List<SabZone> = listOf(),
    val unlocks: List<SabUnlock> = listOf(),
    val songs: List<SabSong> = listOf(),
)
