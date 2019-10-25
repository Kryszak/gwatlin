package com.kryszak.gwatlin.clients.dailyrewards

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.kryszak.gwatlin.clients.exception.RetrieveError
import com.kryszak.gwatlin.http.BaseHttpClient
import com.kryszak.gwatlin.http.exception.ErrorResponse
import java.util.logging.Logger

internal class DailyRewardsClient : BaseHttpClient() {

    companion object {
        val log: Logger = Logger.getLogger(DailyRewardsClient::class.java.simpleName)
    }

    private val dailyCraftingEndpoint: String = "dailycrafting"

    private val mapChestsEndpoint: String = "mapchests"

    private val worldBossesEndpoint: String = "worldbosses"

    fun getDailyCraftingRecipesIds(): List<String> {
        val (_, response, result) = "$baseUrl/$dailyCraftingEndpoint"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<String>>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    fun getMapChestsIds(): List<String> {
        val (_, response, result) = "$baseUrl/$mapChestsEndpoint"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<String>>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    fun getWorldBossesIds(): List<String> {
        val (_, response, result) = "$baseUrl/$worldBossesEndpoint"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<String>>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }
}