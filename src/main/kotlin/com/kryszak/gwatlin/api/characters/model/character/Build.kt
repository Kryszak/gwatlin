package com.kryszak.gwatlin.api.characters.model.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for build infos
 */
@Serializable
data class Build(
    val name: String,
    val profession: String,
    val specializations: List<Specialization>,
    val skills: Skills,
    @SerialName("aquatic_skills")
    val aquaticSkills: Skills
)