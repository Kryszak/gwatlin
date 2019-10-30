package com.kryszak.gwatlin.api.gamemechanics.model.trait

import com.google.gson.annotations.SerializedName
import com.kryszak.gwatlin.api.gamemechanics.model.skill.TraitedFact

/**
 * Data model for trait object
 */
data class Trait(
        val id: Int,
        val name: String,
        val icon: String,
        val description: String,
        val specialization: Int,
        val tier: TraitTier,
        val order: Int,
        val slot: TraitSlot,
        val facts: List<TraitFact>,
        @SerializedName("traited_facts") val traitedFacts: List<TraitedFact>,
        val skills: List<TraitSkill>
)
