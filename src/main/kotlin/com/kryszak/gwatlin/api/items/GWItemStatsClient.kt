package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.items.model.stats.ItemStats
import com.kryszak.gwatlin.clients.items.ItemStatsClient

/**
 * Client for item statistics endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWItemStatsClient {

    private val itemStatsClient: ItemStatsClient = ItemStatsClient()

    /**
     * Retrieves ids of all item statistics
     */
    fun getItemStatsIds(): List<Int> {
        return itemStatsClient.getItemStatIds()
    }

    /**
     * Retrieves specific item statistics
     * @param ids of statistics
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.items.model.stats.ItemStats
     */
    fun getItemStats(ids: List<Int>, language: String = "en"): List<ItemStats> {
        return itemStatsClient.getItemStats(ids, language)
    }
}
