package com.kryszak.gwatlin.clients.homeinstance

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.kryszak.gwatlin.api.homeinstance.model.Cat
import com.kryszak.gwatlin.clients.exception.RetrieveError
import com.kryszak.gwatlin.http.BaseHttpClient
import com.kryszak.gwatlin.http.exception.ErrorResponse
import java.util.logging.Logger

internal class HomeInstanceClient : BaseHttpClient() {

    companion object {
        val log: Logger = Logger.getLogger(HomeInstanceClient::class.java.simpleName)
    }

    private val baseEndpoint: String = "home"

    private val catsEndpoint: String = "$baseEndpoint/cats"

    private val nodesEndpoint: String = "$baseEndpoint/nodes"

    fun getCatIds(): List<Int> {
        val (_, response, result) = "$baseUrl/$catsEndpoint"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<Int>>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    fun getCat(id: Int): Cat {
        val (_, response, result) = "$baseUrl/$catsEndpoint/$id"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<Cat>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    fun getCats(ids: List<Int>): List<Cat> {
        val params = ids.joinToString(",")
        val (_, response, result) = "$baseUrl/$catsEndpoint?ids=$params"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<Cat>>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    fun getNodesIds(): List<String> {
        val (_, response, result) = "$baseUrl/$nodesEndpoint"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<List<String>>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }
}