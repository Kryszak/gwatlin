package com.kryszak.gwatlin.api.characters.model.character

import com.google.gson.annotations.SerializedName

data class Build(
    val name: String,
    val profession: String,
    val specializations: List<CharacterSpecialization>,
    val skills: CharacterSkills,
    @SerializedName("aquatic_skills")
    val aquaticSkills: CharacterSkills
)