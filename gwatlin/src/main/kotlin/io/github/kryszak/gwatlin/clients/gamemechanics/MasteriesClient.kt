package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class MasteriesClient : BaseHttpClient() {

    private val masteriesEndpoint: String = "/masteries"

    fun getMasteriesIds(): List<Int> {
        return getRequest(masteriesEndpoint)
    }

    fun getMastery(id: Int, language: ApiLanguage?): Mastery {
        return getRequest("$masteriesEndpoint/$id", listOf(), language)
    }

    fun getMasteries(ids: List<Int>, language: ApiLanguage?): List<Mastery> {
        val params = ids.joinToString(",")
        return getRequest("$masteriesEndpoint?ids=$params", listOf(), language)
    }

    fun getAllMasteries(language: ApiLanguage?): List<Mastery> {
        return getRequest("$masteriesEndpoint?ids=all", listOf(), language)
    }
}
