package com.kryszak.gwatlin.api.gamemechanics.model.trait

import com.google.gson.annotations.SerializedName
import com.kryszak.gwatlin.api.gamemechanics.model.skill.TraitedFact

/**
 * Data model for trait skills property
 */
data class TraitSkill(
        val id: Int,
        val name: String,
        val description: String,
        val icon: String,
        val facts: List<TraitFact>?,
        @SerializedName("traited_facts") val traitedFacts: List<TraitedFact>?
)
