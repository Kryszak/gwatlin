package io.github.kryszak.gwatlin.clients.items

import io.github.kryszak.gwatlin.api.items.model.pvp.amulet.PvpAmulet
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class PvpAmuletsClient : BaseHttpClient() {

    private val amuletsEndpoint = "/pvp/amulets"

    fun getPvpAmuletIds(): List<Int> {
        return getRequest(amuletsEndpoint)
    }

    fun getPvpAmulets(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<PvpAmulet> {
        val params = ids.joinToString(",")
        return getRequest("$amuletsEndpoint?ids=$params", language)
    }
}