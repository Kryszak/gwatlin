package io.github.kryszak.gwatlin.api.achievement.model

import kotlinx.serialization.Serializable

/**
 * Data model for achievement tier object
 */
@Serializable
data class AchievementTier(
        val count: Int,
        val points: Int
)