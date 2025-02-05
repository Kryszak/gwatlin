package io.github.kryszak.gwatlin.clients.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.wardrobe.model.JadeBot
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class JadeBotsClient : BaseHttpClient() {

    private val endpoint = "/jadebots"

    fun getJadeBotIds(): List<Int> {
        return getRequest(endpoint)
    }

    fun getJadeBot(id: Int, language: ApiLanguage?): JadeBot {
        return getRequest("$endpoint/$id", listOf(), language)
    }

    fun getJadeBots(ids: List<Int>, language: ApiLanguage?): List<JadeBot> {
        val params = ids.joinToString(",")
        return getRequest("$endpoint?ids=$params", listOf(), language)
    }
}