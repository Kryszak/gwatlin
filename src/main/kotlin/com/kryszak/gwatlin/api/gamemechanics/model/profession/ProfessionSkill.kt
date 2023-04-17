package com.kryszak.gwatlin.api.gamemechanics.model.profession

import kotlinx.serialization.Serializable

@Serializable
data class ProfessionSkill(
    val id: Int,
    val slot: SkillSlot,
    val type: String,
    val attunement: String? = null,
    val source: String? = null
)
