package com.kryszak.gwatlin.api.gamemechanics.model.profession

import kotlinx.serialization.Serializable

/**
 * Data model for skill property of weapon object
 */
@Serializable
data class WeaponSkill(
        val id: Int,
        val slot: WeaponSkillSlot,
        val offhand: String?,
        val attunement: String?,
        val source: String?
)
