package com.kryszak.gwatlin.achievements

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.kryszak.gwatlin.http.BaseHttpClient

class AchievementsClient : BaseHttpClient() {

    private val baseEndpoint: String = "achievements"

    fun getAchievementIdsList(): List<Int> {
        val (_, _, result) = "$baseUrl/$baseEndpoint"
                .httpGet()
                .responseObject<List<Int>>()

        return processResult(result)
    }
}
