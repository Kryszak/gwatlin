package com.kryszak.gwatlin.api.characters.model.character.sab

data class CharacterSAB(
    val zones: List<SabZone>,
    val unlocks: List<SabUnlock>,
    val songs: List<SabSong>
)
