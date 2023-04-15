package com.kryszak.gwatlin.api.achievement.model

import kotlinx.serialization.Serializable

/**
 * Data model for achievement reward object
 */
@Serializable
data class AchievementReward(
        val int: Int,
        val type: String,
        val region: String?,
        val count: Int
)