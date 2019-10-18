package com.kryszak.gwatlin.api.model.achievement.daily

data class DailyAchievementList(val pve: List<DailyAchievement>,
                                val pvp: List<DailyAchievement>,
                                val www: List<DailyAchievement>,
                                val fractals: List<DailyAchievement>,
                                val special: List<DailyAchievement>)