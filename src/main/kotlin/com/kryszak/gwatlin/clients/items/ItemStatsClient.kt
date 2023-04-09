package com.kryszak.gwatlin.clients.items

import com.kryszak.gwatlin.api.items.model.stats.ItemStats
import com.kryszak.gwatlin.http.BaseHttpClient

internal class ItemStatsClient : BaseHttpClient() {

    private val statsEndpoint = "itemstats"

    fun getItemStatIds(): List<Int> {
        return getRequest(statsEndpoint)
    }

    fun getItemStats(ids: List<Int>, language: String): List<ItemStats> {
        val params = ids.joinToString(",")
        return getRequest("$statsEndpoint?ids=$params", language)
    }
}
