package io.github.kryszak.gwatlin.api.gamemechanics.model.trait

import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.Fact
import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.TraitedFact
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
    val facts: List<Fact>?,
    @SerialName("traited_facts") val traitedFacts: List<@Serializable(with = TraitedFact.TraitedFactUnwrapSerializer::class) TraitedFact>?,
)
