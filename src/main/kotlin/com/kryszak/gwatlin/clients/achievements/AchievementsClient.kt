package com.kryszak.gwatlin.clients.achievements

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.kryszak.gwatlin.clients.exception.RetrieveError
import com.kryszak.gwatlin.api.achievement.model.Achievement
import com.kryszak.gwatlin.api.achievement.model.category.AchievementCategory
import com.kryszak.gwatlin.api.achievement.model.daily.DailyAchievementList
import com.kryszak.gwatlin.api.achievement.model.group.AchievementGroup
import com.kryszak.gwatlin.http.BaseHttpClient
import com.kryszak.gwatlin.http.exception.ErrorResponse
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

    fun getAchievementIdsList(): List<Int> {
        val (_, response, result) = "$baseUrl/$baseEndpoint"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<Int>>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    fun getAchievementByIds(ids: List<Int>): List<Achievement> {
        val params = ids.joinToString(",")
        val (_, response, result) = "$baseUrl/$baseEndpoint?ids=$params"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<Achievement>>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    fun getDailyAchievements(): DailyAchievementList {
        val (_, response, result) = "$baseUrl/$dailyEndpoint"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<DailyAchievementList>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    fun getTomorrowDailyAchievements(): DailyAchievementList {
        val (_, response, result) = "$baseUrl/$dailyTomorrowEndpoint"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<DailyAchievementList>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    fun getAchievementGroupIds(): List<String> {
        val (_, response, result) = "$baseUrl/$groupEndpoint"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<String>>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    fun getAchievementGroup(id: String): AchievementGroup {
        val (_, response, result) = "$baseUrl/$groupEndpoint/$id"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<AchievementGroup>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    fun getAchievementCategories(): List<Int> {
        val (_, response, result) = "$baseUrl/$categoryEndpoint"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<Int>>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    fun getAchievementCategory(id: Int): AchievementCategory {
        val (_, response, result) = "$baseUrl/$categoryEndpoint/$id"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<AchievementCategory>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }
}
