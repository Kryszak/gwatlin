package io.github.kryszak.gwatlin.api.items

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.items.model.item.Item
import io.github.kryszak.gwatlin.clients.items.ItemsClient

/**
 * Client for items endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/items)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
     * @see io.github.kryszak.gwatlin.api.items.model.item.Item
     */
    @JvmOverloads
    fun getItems(ids: List<Int>, language: ApiLanguage? = null): List<Item> {
        return itemClient.getItems(ids, language)
    }
}
