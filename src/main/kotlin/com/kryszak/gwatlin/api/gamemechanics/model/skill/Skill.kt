package com.kryszak.gwatlin.api.gamemechanics.model.skill

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for skill object
 */
@Serializable
data class Skill(
        val id: Int,
        val name: String,
        val description: String?,
        val icon: String,
        @SerialName("weapon_type") val weaponType: String,
        @SerialName("chat_link") val chatLink: String,
        val type: SkillType,
        @SerialName("skill_type") val skillType: String?,
        val professions: List<String>,
        val slot: SkillSlot,
        val facts: List<SkillFact>,
        val flags: List<String>,
        @SerialName("traited_facts") val traitedFacts: List<TraitedFact>,
        val categories: List<String>?,
        val attunement: String?,
        val cost: Int?,
        @SerialName("dual_wield") val dualWield: String?,
        @SerialName("flip_skill") val flipSkill: Int?,
        val initiative: Int?,
        @SerialName("next_chain") val nextChain: Int?,
        @SerialName("prev_chain") val previousChain: Int?
)
