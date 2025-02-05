package io.github.kryszak.gwatlin.clients.items

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.items.model.stats.ItemStats
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class ItemStatsClient : BaseHttpClient() {

    private val statsEndpoint = "/itemstats"

    fun getItemStatIds(): List<Int> {
        return getRequest(statsEndpoint)
    }

    fun getItemStats(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<ItemStats> {
        val params = ids.joinToString(",")
        return getRequest("$statsEndpoint?ids=$params", language)
    }

    fun getPagedItemStats(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<ItemStats>> {
        return getPagedRequest("$statsEndpoint?page=${pageRequest.page}&page_size=${pageRequest.size}", language)
    }
}
