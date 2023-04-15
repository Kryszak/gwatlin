package com.kryszak.gwatlin.api.achievement.model.daily

import kotlinx.serialization.Serializable

/**
 * Data model for daily achievement list object
 */
@Serializable
data class DailyAchievementList(
        val pve: List<DailyAchievement>,
        val pvp: List<DailyAchievement>,
        val www: List<DailyAchievement>,
        val fractals: List<DailyAchievement>,
        val special: List<DailyAchievement>
)