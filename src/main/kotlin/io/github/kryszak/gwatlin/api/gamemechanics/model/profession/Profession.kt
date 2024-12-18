package io.github.kryszak.gwatlin.api.gamemechanics.model.profession

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for profession object
 */
@Serializable
data class Profession(
        val id: String,
        val name: String,
        val icon: String,
        @SerialName("icon_big") val iconBig: String,
        val flags: List<String> = listOf(),
        val specializations: List<Int> = listOf(),
        val training: List<Training> = listOf(),
        val skills: List<ProfessionSkill> = listOf(),
        val weapons: ProfessionWeapons
)
