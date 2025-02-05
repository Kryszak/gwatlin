package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.raid.Raid
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class RaidClient : BaseHttpClient() {

    private val raidsEndpoint = "/raids"

    fun getAllRaids(language: ApiLanguage?): List<Raid> {
        return getRequest("$raidsEndpoint?ids=all", listOf(), language)
    }

    fun getRaidIds(): List<String> {
        return getRequest(raidsEndpoint)
    }

    fun getRaid(id: String, language: ApiLanguage?): Raid {
        return getRequest("$raidsEndpoint/$id", listOf(), language)
    }

    fun getRaids(ids: List<String>, language: ApiLanguage?): List<Raid> {
        val params = ids.joinToString(",")
        return getRequest("$raidsEndpoint?ids=$params", listOf(), language)
    }
}