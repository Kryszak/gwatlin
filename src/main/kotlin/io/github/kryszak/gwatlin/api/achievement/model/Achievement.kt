package io.github.kryszak.gwatlin.api.achievement.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for achievement object
 */
@Serializable
data class Achievement(
        val id: Int,
        val name: String,
        val description: String,
        val requirement: String,
        @SerialName("locked_text") val lockedText: String,
        val type: String,
        val flags: List<String> = listOf(),
        val tiers: List<AchievementTier> = listOf(),
        val rewards: List<AchievementReward> = listOf(),
        val bits: List<AchievementBit> = listOf()
)