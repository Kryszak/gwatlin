package io.github.kryszak.gwatlin.api.characters.model.character

import kotlinx.serialization.Serializable

/**
 * Data model for character specializations
 */
@Serializable
data class CharacterSpecialization(
    val specializations: CharacterSpecializations
)

@Serializable
data class CharacterSpecializations(
    val pve: List<Specialization>,
    val pvp: List<Specialization>,
    val wvw: List<Specialization>,
)