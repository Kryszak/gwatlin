package com.kryszak.gwatlin.clients.items

import com.kryszak.gwatlin.api.items.model.pvp.amulet.PvpAmulet
import com.kryszak.gwatlin.http.BaseHttpClient

internal class PvpAmuletsClient : BaseHttpClient() {

    private val amuletsEndpoint = "pvp/amulets"

    fun getPvpAmuletIds(): List<Int> {
        return getRequest(amuletsEndpoint)
    }

    fun getPvpAmulets(ids: List<Int>, language: String): List<PvpAmulet> {
        val params = ids.joinToString(",")
        return getRequest("$amuletsEndpoint?ids=$params", language)
    }
}