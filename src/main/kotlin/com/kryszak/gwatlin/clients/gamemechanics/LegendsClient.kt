package com.kryszak.gwatlin.clients.gamemechanics

import com.kryszak.gwatlin.api.gamemechanics.model.legend.Legend
import com.kryszak.gwatlin.http.BaseHttpClient

internal class LegendsClient : BaseHttpClient() {

    private val legendsEndpoint: String = "legends"

    fun getLegendIds(): List<String> {
        return getRequest(legendsEndpoint)
    }

    fun getLegends(ids: List<String>, language: String): List<Legend> {
        val params = ids.joinToString(",")
        return getRequest("$legendsEndpoint?ids=$params&lang=$language")
    }
}
