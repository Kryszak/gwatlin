package io.github.kryszak.gwatlin.api.gamemechanics.model.pet

import kotlinx.serialization.Serializable

/**
 * Data model for pet object
 */
@Serializable
data class Pet(
        val id: Int,
        val name: String,
        val description: String,
        val icon: String,
        val skills: List<PetSkill> = listOf(),
)
