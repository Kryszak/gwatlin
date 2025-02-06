package io.github.kryszak.gwatlin.clients.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.api.wardrobe.model.Glider
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class GlidersClient : BaseHttpClient() {

    private val endpoint = "/gliders"

    fun getGliderIds(): List<Int> {
        return getRequest(endpoint)
    }

    fun getGlider(id: Int, language: ApiLanguage?): Glider {
        return getRequest("$endpoint/$id", language)
    }

    fun getGliders(ids: List<Int>, language: ApiLanguage?): List<Glider> {
        val params = ids.joinToString(",")
        return getRequest("$endpoint?ids=$params", language)
    }

    fun getPagedGliders(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<Glider>> {
        return getPagedRequest("$endpoint?${pageRequest.toQueryParams()}", language)
    }
}