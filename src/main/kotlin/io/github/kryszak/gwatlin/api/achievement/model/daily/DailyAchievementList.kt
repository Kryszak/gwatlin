package io.github.kryszak.gwatlin.api.achievement.model.daily

import kotlinx.serialization.Serializable

/**
 * Data model for daily achievement list object
 */
@Serializable
data class DailyAchievementList(
        val pve: List<DailyAchievement> = listOf(),
        val pvp: List<DailyAchievement> = listOf(),
        val wvw: List<DailyAchievement> = listOf(),
        val fractals: List<DailyAchievement> = listOf(),
        val special: List<DailyAchievement> = listOf()
)