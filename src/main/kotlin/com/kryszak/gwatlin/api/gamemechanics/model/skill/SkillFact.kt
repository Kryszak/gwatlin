package com.kryszak.gwatlin.api.gamemechanics.model.skill

import kotlinx.serialization.Serializable

/**
 * Data model for facts property of skill object
 */
@Serializable
data class SkillFact(
    val text: String,
    val icon: String,
    val type: String,
    val value: Int
)
