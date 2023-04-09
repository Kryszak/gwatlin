package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.items.model.item.Item
import com.kryszak.gwatlin.clients.items.ItemsClient

/**
 * Client for items endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWItemsClient {

    private val itemClient: ItemsClient = ItemsClient()

    /**
     * Retrieves all item ids
     */
    fun getItemIds(): List<Int> {
        return itemClient.getItemIds()
    }

    /**
     * Retrieves specific items
     * @param ids of items
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.items.model.item.Item
     */
    @JvmOverloads
    fun getItems(ids: List<Int>, language: ApiLanguage? = null): List<Item> {
        return itemClient.getItems(ids, language)
    }
}
