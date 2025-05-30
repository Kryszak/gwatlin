package io.github.kryszak.gwatlin.clients.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.api.wardrobe.model.Mini
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class MinisClient : BaseHttpClient() {

    private val minisEndpoint = "/minis"

    fun getAllMinis(language: ApiLanguage?): List<Mini> {
        return getRequest(minisEndpoint, listOf("ids" to "all"), language)
    }

    fun getMiniIds(): List<Int> {
        return getRequest(minisEndpoint)
    }

    fun getMini(id: Int, language: ApiLanguage?): Mini {
        return getRequest("$minisEndpoint/$id", listOf(), language)
    }

    fun getMinis(ids: List<Int>, language: ApiLanguage?): List<Mini> {
        val params = ids.joinToString(",")
        return getRequest(minisEndpoint, listOf("ids" to params), language)
    }

    fun getPagedMinis(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<Mini>> {
        return getPagedRequest(minisEndpoint, pageRequest.toQueryParams(), language)
    }
}