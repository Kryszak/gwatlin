package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class MasteriesClient : BaseHttpClient() {

    private val masteriesEndpoint: String = "/masteries"

    fun getMasteriesIds(): List<Int> {
        return getRequest(masteriesEndpoint)
    }

    fun getMastery(id: Int, language: io.github.kryszak.gwatlin.api.ApiLanguage?): Mastery {
        return getRequest("$masteriesEndpoint/$id", language)
    }

    fun getMasteries(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Mastery> {
        val params = ids.joinToString(",")
        return getRequest("$masteriesEndpoint?ids=$params", language)
    }

    fun getAllMasteries(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Mastery> {
        return getRequest("$masteriesEndpoint?ids=all", language)
    }
}
