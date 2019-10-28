package com.kryszak.gwatlin.api.gamemechanics.model.pet

/**
 * Data model for pet object
 */
data class Pet(
        val id: Int,
        val name: String,
        val description: String,
        val icon: String,
        val skills: List<PetSkill>
)
