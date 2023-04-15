package com.kryszak.gwatlin.api.achievement.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for achievement object
 */
@Serializable
data class Achievement(
        val id: String,
        val name: String,
        val description: String,
        val requirement: String,
        @SerialName("locked_text") val lockedText: String,
        val type: String,
        val flags: List<String>,
        val tiers: List<AchievementTier>,
        val rewards: List<AchievementReward>
)