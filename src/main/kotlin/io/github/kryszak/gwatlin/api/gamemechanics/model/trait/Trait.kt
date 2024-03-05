package io.github.kryszak.gwatlin.api.gamemechanics.model.trait

import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.Fact
import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.TraitedFact
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
    @SerialName("traited_facts") val traitedFacts: List<@Serializable(with = TraitedFact.TraitedFactUnwrapSerializer::class) TraitedFact> = listOf(),
    val skills: List<TraitSkill> = listOf(),
)
