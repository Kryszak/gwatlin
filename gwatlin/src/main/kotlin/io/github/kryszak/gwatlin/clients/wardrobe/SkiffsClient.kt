package io.github.kryszak.gwatlin.clients.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.wardrobe.model.Skiff
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class SkiffsClient : BaseHttpClient() {

    private val endpoint = "/skiffs"

    fun getSkiffIds(): List<Int> {
        return getRequest(endpoint)
    }

    fun getSkiff(id: Int, language: ApiLanguage?): Skiff {
        return getRequest("$endpoint/$id", language)
    }

    fun getSkiffs(ids: List<Int>, language: ApiLanguage?): List<Skiff> {
        val params = ids.joinToString(",")
        return getRequest("$endpoint?ids=$params", language)
    }
}