package com.kryszak.gwatlin.api.achievement.model

/**
 * Data model for achievement reward object
 */
data class AchievementReward(
        val int: Int,
        val type: String,
        val region: String?,
        val count: Int
)