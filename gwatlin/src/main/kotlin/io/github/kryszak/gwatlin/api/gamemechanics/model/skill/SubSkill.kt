package io.github.kryszak.gwatlin.api.gamemechanics.model.skill

import kotlinx.serialization.Serializable

/**
 * Data class for subskill field
 */
@Serializable
data class SubSkill(
    val id: Int,
    val attunement: String,
)
