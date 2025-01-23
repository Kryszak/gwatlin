package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.legend.Legend
import io.github.kryszak.gwatlin.clients.gamemechanics.LegendsClient

/**
 * Client for game mechanic - legends endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/legends).
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWLegendsClient {

    private val legendsClient: LegendsClient = LegendsClient()

    /**
     * Retrieves list of all legend ids
     * @return legend ids list
     */
    fun getLegendIds(): List<String> {
        return legendsClient.getLegendIds()
    }

    /**
     * Retrieves list of legends
     * @param ids list of legend ids
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.legend.Legend
     */
    @JvmOverloads
    fun getLegends(ids: List<String>, language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<Legend> {
        return legendsClient.getLegends(ids, language)
    }
}
