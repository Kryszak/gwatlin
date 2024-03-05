package io.github.kryszak.gwatlin.api.gamemechanics.model.skill

import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.Fact
import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.TraitedFact
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for skill object
 */
@Serializable
data class Skill(
        val id: Int,
        val name: String,
        val description: String? = null,
        val icon: String,
        @SerialName("weapon_type") val weaponType: String,
        @SerialName("chat_link") val chatLink: String,
        val type: SkillType,
        @SerialName("skill_type") val skillType: String? = null,
        val professions: List<String>,
        val slot: SkillSlot,
        val facts: List<Fact> = listOf(),
        val flags: List<String> = listOf(),
        @SerialName("traited_facts") val traitedFacts: List<@Serializable(with = TraitedFact.TraitedFactUnwrapSerializer::class) TraitedFact> = listOf(),
        val categories: List<String> = listOf(),
        val attunement: String? = null,
        val cost: Int? = null,
        @SerialName("dual_wield") val dualWield: String? = null,
        @SerialName("flip_skill") val flipSkill: Int? = null,
        val initiative: Int? = null,
        @SerialName("next_chain") val nextChain: Int? = null,
        @SerialName("prev_chain") val previousChain: Int? = null,
)
