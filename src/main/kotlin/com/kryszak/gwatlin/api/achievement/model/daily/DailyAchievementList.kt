package com.kryszak.gwatlin.api.achievement.model.daily

/**
 * Data model for daily achievement list object
 */
data class DailyAchievementList(
        val pve: List<DailyAchievement>,
        val pvp: List<DailyAchievement>,
        val www: List<DailyAchievement>,
        val fractals: List<DailyAchievement>,
        val special: List<DailyAchievement>
)