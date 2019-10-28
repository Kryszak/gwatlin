package com.kryszak.gwatlin.api.gamemechanics.model.profession

/**
 * Data model for weapon profession's property
 */
data class Weapon(
        val specialization: Int,
        val skills: List<WeaponSkill>,
        val flags: List<String>
)
