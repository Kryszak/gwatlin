package com.kryszak.gwatlin.api.characters.model.character

import com.google.gson.annotations.SerializedName

/**
 * Data model for build infos
 */
data class Build(
    val name: String,
    val profession: String,
    val specializations: List<Specialization>,
    val skills: Skills,
    @SerializedName("aquatic_skills")
    val aquaticSkills: Skills
)