package com.kryszak.gwatlin.clients.gamemechanics

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
import com.kryszak.gwatlin.http.BaseHttpClient

internal class MasteriesClient : BaseHttpClient() {

    private val masteriesEndpoint: String = "masteries"

    fun getMasteriesIds(): List<Int> {
        return getRequest(masteriesEndpoint)
    }

    fun getMastery(id: Int, language: ApiLanguage?): Mastery {
        return getRequest("$masteriesEndpoint/$id", language)
    }

    fun getMasteries(ids: List<Int>, language: ApiLanguage?): List<Mastery> {
        val params = ids.joinToString(",")
        return getRequest("$masteriesEndpoint?ids=$params", language)
    }

    fun getAllMasteries(language: ApiLanguage?): List<Mastery> {
        return getRequest("$masteriesEndpoint?ids=all", language)
    }
}
