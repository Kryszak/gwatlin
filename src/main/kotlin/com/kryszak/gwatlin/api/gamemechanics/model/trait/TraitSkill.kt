package com.kryszak.gwatlin.api.gamemechanics.model.trait

import com.kryszak.gwatlin.api.gamemechanics.model.skill.TraitedFact
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for trait skills property
 */
@Serializable
data class TraitSkill(
        val id: Int,
        val name: String,
        val description: String,
        val icon: String,
        val facts: List<TraitFact>?,
        @SerialName("traited_facts") val traitedFacts: List<TraitedFact>?
)
