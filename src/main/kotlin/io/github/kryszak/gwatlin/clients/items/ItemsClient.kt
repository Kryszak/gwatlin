package io.github.kryszak.gwatlin.clients.items

import io.github.kryszak.gwatlin.api.items.model.item.Item
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class ItemsClient : BaseHttpClient() {

    private val itemEndpoint = "/items"

    fun getItemIds(): List<Int> {
        return getRequest(itemEndpoint)
    }

    fun getItems(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Item> {
        val params = ids.joinToString(",")
        return getRequest("$itemEndpoint?ids=$params", language)
    }
}
