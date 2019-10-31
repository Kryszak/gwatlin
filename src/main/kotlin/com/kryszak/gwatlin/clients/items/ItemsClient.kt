package com.kryszak.gwatlin.clients.items

import com.kryszak.gwatlin.api.items.model.item.Item
import com.kryszak.gwatlin.http.BaseHttpClient

internal class ItemsClient : BaseHttpClient() {

    private val itemEndpoint = "items"

    fun getItemIds(): List<Int> {
        return getRequest(itemEndpoint)
    }

    fun getItems(ids: List<Int>, language: String): List<Item> {
        val params = ids.joinToString(",")
        return getRequest("$itemEndpoint?ids=$params&lang=$language")
    }
}
