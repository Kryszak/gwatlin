package com.kryszak.gwatlin.api.characters.model.character

data class CharacterSpecialization(
    val id: Int,
    val traits: Set<Int>
)