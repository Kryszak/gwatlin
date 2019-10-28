package com.kryszak.gwatlin.api.achievement.model

import com.google.gson.annotations.SerializedName

/**
 * Data model for achievement object
 */
data class Achievement(
        val id: String,
        val name: String,
        val description: String,
        val requirement: String,
        @SerializedName("locked_text") val lockedText: String,
        val type: String,
        val flags: List<String>,
        val tiers: List<AchievementTier>,
        val rewards: List<AchievementReward>
)