package com.kryszak.gwatlin.api.characters.model.character

data class CharacterSkills(
    val heal: Int,
    val utilities: List<Int>,
    val elite: Int,
    val legends: List<String>?
)
