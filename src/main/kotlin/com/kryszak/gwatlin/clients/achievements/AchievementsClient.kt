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
        return getRequest("$baseUrl/$baseEndpoint")
    }

    fun getAchievementByIds(ids: List<Int>): List<Achievement> {
        val params = ids.joinToString(",")
        return getRequest("$baseUrl/$baseEndpoint?ids=$params")
    }

    fun getDailyAchievements(): DailyAchievementList {
        return getRequest("$baseUrl/$dailyEndpoint")
    }

    fun getTomorrowDailyAchievements(): DailyAchievementList {
        return getRequest("$baseUrl/$dailyTomorrowEndpoint")
    }

    fun getAchievementGroupIds(): List<String> {
        return getRequest("$baseUrl/$groupEndpoint")
    }

    fun getAchievementGroup(id: String): AchievementGroup {
        return getRequest("$baseUrl/$groupEndpoint/$id")
    }

    fun getAchievementCategories(): List<Int> {
        return getRequest("$baseUrl/$categoryEndpoint")
    }

    fun getAchievementCategory(id: Int): AchievementCategory {
        return getRequest("$baseUrl/$categoryEndpoint/$id")
    }
}
