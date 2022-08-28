package com.kryszak.gwatlin.api.characters.model.character

data class Skills(
    val heal: Int,
    val utilities: List<Int>,
    val elite: Int,
    val legends: List<String>?
)
