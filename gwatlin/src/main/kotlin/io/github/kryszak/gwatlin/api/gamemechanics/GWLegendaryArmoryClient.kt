package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.legend.LegendaryArmoryItem
import io.github.kryszak.gwatlin.clients.gamemechanics.LegendaryArmoryClient

/**
 * Client for legendary armory endpoint
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWLegendaryArmoryClient {

    private val legendaryArmoryClient = LegendaryArmoryClient()

    /**
     * Returns list of all ids for legendary armory
     */
    fun getLegendaryArmoryIds(): List<Long> {
        return legendaryArmoryClient.getLegendaryArmoryIds()
    }

    /**
     * Returns legendary armory item for given
     * @param id of legendary item
     */
    fun getLegendaryArmoryItem(id: Long): LegendaryArmoryItem {
        return legendaryArmoryClient.getLegendaryArmoryItem(id)
    }

    /**
     * Returns legendary armory items for given
     * @param ids of legendary items
     */
    fun getLegendaryArmoryItems(ids: List<Long>): List<LegendaryArmoryItem> {
        return legendaryArmoryClient.getLegendaryArmoryItems(ids)
    }

    /**
     * Returns list of all legendary armory items
     */
    fun getAllLegendaryArmoryItems(): List<LegendaryArmoryItem> {
        return legendaryArmoryClient.getAllLegendaryArmoryItems()
    }
}