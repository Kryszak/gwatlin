package io.github.kryszak.gwatlin.api.gamemechanics.model.profession

import kotlinx.serialization.Serializable

/**
 * Data model for skill property of weapon object
 */
@Serializable
data class WeaponSkill(
        val id: Int,
        val slot: SkillSlot,
        val offhand: String? = null,
        val attunement: String? = null,
        val source: String? = null
)
