package com.kryszak.gwatlin.api.gamemechanics.model.profession

import kotlinx.serialization.Serializable

/**
 * Data model for skill property of profession object
 */
@Serializable
data class ProfessionSkill(
    val id: Int,
    val slot: SkillSlot,
    val type: String,
    val attunement: String? = null,
    val source: String? = null
)
