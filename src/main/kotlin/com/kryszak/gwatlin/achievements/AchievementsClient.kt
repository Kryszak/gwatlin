package com.kryszak.gwatlin.achievements

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.kryszak.gwatlin.api.model.achievement.Achievement
import com.kryszak.gwatlin.api.model.achievement.category.AchievementCategory
import com.kryszak.gwatlin.api.model.achievement.daily.DailyAchievementList
import com.kryszak.gwatlin.api.model.achievement.group.AchievementGroup
import com.kryszak.gwatlin.http.BaseHttpClient
import java.util.logging.Logger

internal class AchievementsClient : BaseHttpClient() {

    companion object {
        val log: Logger = Logger.getLogger(AchievementsClient::class.java.simpleName)
    }

    private val baseEndpoint: String = "achievements"

    private val dailyEndpoint: String = "$baseEndpoint/daily"

    private val dailyTomorrowEndpoint: String = "$dailyEndpoint/tomorrow"

    private val groupEndpoint: String = "$baseEndpoint/groups"

    private val categoryEndpoint: String = "$baseEndpoint/categories"

    private val logMessage = "Requested url: %s"

    fun getAchievementIdsList(): List<Int> {
        val (_, _, result) = "$baseUrl/$baseEndpoint"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<Int>>()

        return processResult(result)
    }

    fun getAchievementByIds(ids: List<Int>): List<Achievement> {
        val params = ids.joinToString(",")
        val (_, _, result) = "$baseUrl/$baseEndpoint?ids=$params"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<Achievement>>()

        return processResult(result)
    }

    fun getDailyAchievements(): DailyAchievementList {
        val (_, _, result) = "$baseUrl/$dailyEndpoint"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<DailyAchievementList>()

        return processResult(result)
    }

    fun getTomorrowDailyAchievements(): DailyAchievementList {
        val (_, _, result) = "$baseUrl/$dailyTomorrowEndpoint"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<DailyAchievementList>()

        return processResult(result)
    }

    fun getAchievementGroupIds(): List<String> {
        val (_, _, result) = "$baseUrl/$groupEndpoint"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<String>>()

        return processResult(result)
    }

    fun getAchievementGroup(id: String): AchievementGroup {
        val (_, _, result) = "$baseUrl/$groupEndpoint/$id"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<AchievementGroup>()

        return processResult(result)
    }

    fun getAchievementCategories(): List<Int> {
        val (_, _, result) = "$baseUrl/$categoryEndpoint"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<Int>>()

        return processResult(result)
    }

    fun getAchievementCategory(id: Int): AchievementCategory {
        val (_, _, result) = "$baseUrl/$categoryEndpoint/$id"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<AchievementCategory>()

        return processResult(result)
    }
}
