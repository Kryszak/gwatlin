package io.github.kryszak.gwatlin.clients.items

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.items.model.item.Item
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class ItemsClient : BaseHttpClient() {

    private val itemEndpoint = "/items"

    fun getItemIds(): List<Int> {
        return getRequest(itemEndpoint)
    }

    fun getItems(ids: List<Int>, language: ApiLanguage?): List<Item> {
        val params = ids.joinToString(",")
        return getRequest(itemEndpoint, listOf("ids" to params), language)
    }

    fun getPagedItems(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<Item>> {
        return getPagedRequest(itemEndpoint, pageRequest.toQueryParams(), language)
    }

    fun getPagedItems(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<Item>> {
        return getPagedRequest("$itemEndpoint?${pageRequest.toQueryParams()}", language)
    }
}
