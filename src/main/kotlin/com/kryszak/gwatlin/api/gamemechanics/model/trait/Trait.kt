package com.kryszak.gwatlin.api.gamemechanics.model.trait

import com.kryszak.gwatlin.api.gamemechanics.model.skill.TraitedFact
import com.kryszak.gwatlin.api.gamemechanics.model.facts.Fact
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for trait object
 */
@Serializable
data class Trait(
    val id: Int,
    val name: String,
    val icon: String,
    val description: String,
    val specialization: Int,
    val tier: TraitTier,
    val order: Int,
    val slot: TraitSlot,
    val facts: List<Fact> = listOf(),
    @SerialName("traited_facts") val traitedFacts: List<TraitedFact> = listOf(),
    val skills: List<TraitSkill> = listOf()
)
