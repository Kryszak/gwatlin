package com.kryszak.gwatlin.api.gamemechanics.model.skill

import com.google.gson.annotations.SerializedName

/**
 * Data model for skill object
 */
data class Skill(
        val id: Int,
        val name: String,
        val description: String?,
        val icon: String,
        @SerializedName("chat_link") val chatLink: String,
        val type: SkillType,
        @SerializedName("skill_type") val skillType: String?,
        val professions: List<String>,
        val slot: SkillSlot,
        val facts: List<SkillFact>,
        @SerializedName("traited_facts") val traitedFacts: List<TraitedFact>,
        val categories: List<String>?,
        val attunement: String?,
        val cost: Int?,
        @SerializedName("dual_wield") val dualWield: String?,
        @SerializedName("flip_skill") val flipSkill: Int?,
        val initiative: Int?,
        @SerializedName("next_chain") val nextChain: Int?,
        @SerializedName("prev_chain") val previousChain: Int?
)
