package com.kryszak.gwatlin.clients.gamemechanics

import com.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
import com.kryszak.gwatlin.http.BaseHttpClient

internal class MasteriesClient : BaseHttpClient() {

    private val masteriesEndpoint: String = "masteries"

    fun getMasteriesIds(): List<Int> {
        return getRequest("$baseUrl/$masteriesEndpoint")
    }

    fun getMastery(id: Int, language: String): Mastery {
        return getRequest("$baseUrl/$masteriesEndpoint/$id?lang=$language")
    }

    fun getMasteries(ids: List<Int>, language: String): List<Mastery> {
        val params = ids.joinToString(",")
        return getRequest("$baseUrl/$masteriesEndpoint?ids=$params&lang=$language")
    }

    fun getAllMasteries(language: String): List<Mastery> {
        return getRequest("$baseUrl/$masteriesEndpoint?ids=all&lang=$language")
    }
}