package io.github.kryszak.gwatlin.api.achievement.model.daily

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Data model for daily achievement object
 */
@Serializable
data class DailyAchievement(
        val id: Int,
        val level: DailyAchievementLevel,
        @SerialName("required_access") val requiredAccess: List<String>
)