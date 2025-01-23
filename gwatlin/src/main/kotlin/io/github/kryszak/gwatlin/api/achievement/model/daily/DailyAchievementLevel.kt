package io.github.kryszak.gwatlin.api.achievement.model.daily

import kotlinx.serialization.Serializable

/**
 * Data model for daily achievement level object
 */
@Serializable
data class DailyAchievementLevel(
        val min: Int,
        val max: Int
)