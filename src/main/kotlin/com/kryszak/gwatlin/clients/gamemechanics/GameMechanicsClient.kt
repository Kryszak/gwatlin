package com.kryszak.gwatlin.clients.gamemechanics

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.kryszak.gwatlin.api.gamemechanics.model.Mastery
import com.kryszak.gwatlin.clients.exception.RetrieveError
import com.kryszak.gwatlin.http.BaseHttpClient
import com.kryszak.gwatlin.http.exception.ErrorResponse
import java.util.logging.Logger

internal class GameMechanicsClient : BaseHttpClient() {

    companion object {
        val log: Logger = Logger.getLogger(GameMechanicsClient::class.java.simpleName)
    }

    private val masteriesEndpoint: String = "masteries"

    fun getMasteriesIds(): List<Int> {
        val (_, response, result) = "$baseUrl/$masteriesEndpoint"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<Int>>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    fun getMastery(id: Int, language: String): Mastery {
        val (_, response, result) = "$baseUrl/$masteriesEndpoint/$id?lang=$language"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<Mastery>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    fun getMasteries(ids: List<Int>, language: String): List<Mastery> {
        val params = ids.joinToString(",")
        val (_, response, result) = "$baseUrl/$masteriesEndpoint?ids=$params&lang=$language"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<Mastery>>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    fun getAllMasteries(language: String): List<Mastery> {
        val (_, response, result) = "$baseUrl/$masteriesEndpoint?ids=all&lang=$language"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<Mastery>>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }
}