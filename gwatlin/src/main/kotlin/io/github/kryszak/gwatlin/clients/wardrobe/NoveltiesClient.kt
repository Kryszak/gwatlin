package io.github.kryszak.gwatlin.clients.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.wardrobe.model.Novelty
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class NoveltiesClient : BaseHttpClient() {

    private val noveltiesEndpoint = "/novelties"

    fun getAllNovelties(language: ApiLanguage?): List<Novelty> {
        return getRequest("$noveltiesEndpoint?ids=all", language)
    }

    fun getNoveltyIds(): List<Int> {
        return getRequest(noveltiesEndpoint)
    }

    fun getNovelty(id: Int, language: ApiLanguage?): Novelty {
        return getRequest("$noveltiesEndpoint/$id", language)
    }

    fun getNovelties(ids: List<Int>, language: ApiLanguage?): List<Novelty> {
        val params = ids.joinToString(",")
        return getRequest("$noveltiesEndpoint?ids=$params", language)
    }
}