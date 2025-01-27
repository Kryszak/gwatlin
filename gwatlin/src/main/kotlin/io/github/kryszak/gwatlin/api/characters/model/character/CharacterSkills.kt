package io.github.kryszak.gwatlin.api.characters.model.character

import kotlinx.serialization.Serializable

/**
 * Data model for all character skills
 */
@Serializable
data class CharacterSkills(
    val skills: CharacterSkillsField
)

/**
 * Data model for all character skills
 */
@Serializable
data class CharacterSkillsField(
    val pve: Skills,
    val pvp: Skills,
    val wvw: Skills,
)
