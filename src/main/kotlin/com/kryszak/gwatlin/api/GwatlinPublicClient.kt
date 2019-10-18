package com.kryszak.gwatlin.api

import com.kryszak.gwatlin.achievements.AchievementsClient
import com.kryszak.gwatlin.api.model.achievement.Achievement
import com.kryszak.gwatlin.api.model.achievement.daily.DailyAchievementList
import com.kryszak.gwatlin.api.model.achievement.group.AchievementGroup

class GwatlinPublicClient {

    private val achievementsClient: AchievementsClient = AchievementsClient()

    fun getAchievementIdsList(): List<Int> {
        return achievementsClient.getAchievementIdsList()
    }

    fun getAchievementsByIds(ids: List<Int>): List<Achievement> {
        return achievementsClient.getAchievementByIds(ids)
    }

    fun getDailyAchievements(): DailyAchievementList {
        return achievementsClient.getDailyAchievements()
    }

    fun getDailyTomorrowAchievements(): DailyAchievementList {
        return achievementsClient.getTomorrowDailyAchievements()
    }

    fun getAchievementGroupIds(): List<String> {
        return achievementsClient.getAchievementGroupIds()
    }

    fun getAchievementGroup(id: String): AchievementGroup {
        return achievementsClient.getAchievementGroup(id)
    }
}
