package io.github.kryszak.gwatlin.api.items

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.items.model.stats.ItemStats
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.clients.items.ItemStatsClient

/**
 * Client for item statistics endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/itemstats)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
     * @see io.github.kryszak.gwatlin.api.items.model.stats.ItemStats
     */
    @JvmOverloads
    fun getItemStats(ids: List<Int>, language: ApiLanguage? = null): List<ItemStats> {
        return itemStatsClient.getItemStats(ids, language)
    }

    /**
     * Retrieves paged item statistics
     * @param language of returned text (default=en)
     */
    fun getPagedItemStats(pageRequest: PageRequest, language: ApiLanguage? = null): PagedResponse<List<ItemStats>> {
        return itemStatsClient.getPagedItemStats(pageRequest, language)
    }
}
