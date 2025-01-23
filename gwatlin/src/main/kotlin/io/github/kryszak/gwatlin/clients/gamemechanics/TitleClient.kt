package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.Title
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class TitleClient: BaseHttpClient() {

    private val titlesEndpoint = "/titles"

    fun getAllTitles(language: ApiLanguage?): List<Title> {
        return getRequest("$titlesEndpoint?ids=all", language)
    }

    fun getTitleIds(): List<Int> {
        return getRequest(titlesEndpoint)
    }

    fun getTitle(id: Int, language: ApiLanguage?): Title {
        return getRequest("$titlesEndpoint/$id", language)
    }

    fun getTitles(ids: List<Int>, language: ApiLanguage?): List<Title> {
        val params = ids.joinToString(",")
        return getRequest("$titlesEndpoint?ids=$params", language)
    }
}