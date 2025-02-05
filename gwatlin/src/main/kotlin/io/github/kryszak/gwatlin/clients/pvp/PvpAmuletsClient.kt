package io.github.kryszak.gwatlin.clients.pvp

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.pvp.model.amulet.PvpAmulet
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class PvpAmuletsClient : BaseHttpClient() {

    private val amuletsEndpoint = "/pvp/amulets"

    fun getPvpAmuletIds(): List<Int> {
        return getRequest(amuletsEndpoint)
    }

    fun getPvpAmulets(ids: List<Int>, language: ApiLanguage?): List<PvpAmulet> {
        val params = ids.joinToString(",")
        return getRequest("$amuletsEndpoint?ids=$params", listOf(), language)
    }
}