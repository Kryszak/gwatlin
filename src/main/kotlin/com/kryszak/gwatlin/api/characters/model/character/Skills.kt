package com.kryszak.gwatlin.api.characters.model.character

/**
 * Data model for skill sets
 */
data class Skills(
    val heal: Int,
    val utilities: Set<Int>,
    val elite: Int,
    val legends: Set<String>? = null
) {
    constructor(heal: Int, utilities: Set<Int>, elite: Int) : this(heal, utilities, elite, null)
}
