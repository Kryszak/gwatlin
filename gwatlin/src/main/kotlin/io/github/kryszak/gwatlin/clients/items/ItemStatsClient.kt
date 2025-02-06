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

    fun getItemStats(ids: List<Int>, language: ApiLanguage?): List<ItemStats> {
        val params = ids.joinToString(",")
        return getRequest(statsEndpoint, listOf("ids" to params), language)
    }

    fun getPagedItemStats(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<ItemStats>> {
        return getPagedRequest(statsEndpoint, pageRequest.toQueryParams(), language)
    }
}
