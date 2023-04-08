package com.kryszak.gwatlin.clients.gamemechanics

import com.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
import com.kryszak.gwatlin.http.BaseHttpClient

internal class MasteriesClient : BaseHttpClient() {

    private val masteriesEndpoint: String = "masteries"

    fun getMasteriesIds(): List<Int> {
        return getRequest(masteriesEndpoint)
    }

    fun getMastery(id: Int, language: String): Mastery {
        return getRequest("$masteriesEndpoint/$id", language)
    }

    fun getMasteries(ids: List<Int>, language: String): List<Mastery> {
        val params = ids.joinToString(",")
        return getRequest("$masteriesEndpoint?ids=$params", language)
    }

    fun getAllMasteries(language: String): List<Mastery> {
        return getRequest("$masteriesEndpoint?ids=all", language)
    }
}
