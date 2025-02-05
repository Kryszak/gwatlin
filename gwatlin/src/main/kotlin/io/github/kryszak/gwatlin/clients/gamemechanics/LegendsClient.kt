package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.legend.Legend
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class LegendsClient : BaseHttpClient() {

    private val legendsEndpoint: String = "/legends"

    fun getLegendIds(): List<String> {
        return getRequest(legendsEndpoint)
    }

    fun getLegends(ids: List<String>, language: ApiLanguage?): List<Legend> {
        val params = ids.joinToString(",")
        return getRequest("$legendsEndpoint?ids=$params", listOf(), language)
    }
}
