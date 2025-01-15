package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.Title
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class TitleClient: BaseHttpClient() {

    private val titlesEndpoint = "/titles"

    fun getTitles(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Title> {
        return getRequest("$titlesEndpoint?ids=all", language)
    }
}