package io.github.kryszak.gwatlin.api.account.model

import io.github.kryszak.gwatlin.api.characters.model.character.Specialization
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BuildStorage(
    val name: String,
    val profession: String,
    val specializations: Specialization,
    val skills: BuildStorageSkill? = null,
    @SerialName("aquatic_skills")
    val aquaticSkills: List<BuildStorageSkill>? = listOf(),
    val legends: List<Int>? = listOf(),
    @SerialName("aquatic_legends")
    val aquaticLegends: List<Int>? = listOf(),
)

@Serializable
data class BuildStorageSkill(
    val heal: Int? = null,
    val utilities: List<Int?> = listOf(),
    val elite: Int? = null,
)