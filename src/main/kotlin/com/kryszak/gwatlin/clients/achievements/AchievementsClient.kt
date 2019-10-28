package com.kryszak.gwatlin.clients.achievements

import com.kryszak.gwatlin.api.achievement.model.Achievement
import com.kryszak.gwatlin.api.achievement.model.category.AchievementCategory
import com.kryszak.gwatlin.api.achievement.model.daily.DailyAchievementList
import com.kryszak.gwatlin.api.achievement.model.group.AchievementGroup
import com.kryszak.gwatlin.http.BaseHttpClient

internal class AchievementsClient : BaseHttpClient() {

    private val baseEndpoint: String = "achievements"

    private val dailyEndpoint: String = "$baseEndpoint/daily"

    private val dailyTomorrowEndpoint: String = "$dailyEndpoint/tomorrow"

    private val groupEndpoint: String = "$baseEndpoint/groups"

    private val categoryEndpoint: String = "$baseEndpoint/categories"

    fun getAchievementIdsList(): List<Int> {
        return getRequest(baseEndpoint)
    }

    fun getAchievementByIds(ids: List<Int>): List<Achievement> {
        val params = ids.joinToString(",")
        return getRequest("$baseEndpoint?ids=$params")
    }

    fun getDailyAchievements(): DailyAchievementList {
        return getRequest(dailyEndpoint)
    }

    fun getTomorrowDailyAchievements(): DailyAchievementList {
        return getRequest(dailyTomorrowEndpoint)
    }

    fun getAchievementGroupIds(): List<String> {
        return getRequest(groupEndpoint)
    }

    fun getAchievementGroup(id: String): AchievementGroup {
        return getRequest("$groupEndpoint/$id")
    }

    fun getAchievementCategories(): List<Int> {
        return getRequest(categoryEndpoint)
    }

    fun getAchievementCategory(id: Int): AchievementCategory {
        return getRequest("$categoryEndpoint/$id")
    }
}
