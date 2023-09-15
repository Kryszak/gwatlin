package com.kryszak.gwatlin.api.gamemechanics.model.profession

import kotlinx.serialization.Serializable

/**
 * Data model for weapon profession's property
 */
@Serializable
data class Weapon(
        val specialization: Int? = null,
        val skills: List<WeaponSkill>,
        val flags: List<String>
)
