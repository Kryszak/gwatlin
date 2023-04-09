package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.gamemechanics.model.legend.Legend
import com.kryszak.gwatlin.clients.gamemechanics.LegendsClient

/**
 * Client for game mechanic - legends endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
     * @see com.kryszak.gwatlin.api.gamemechanics.model.legend.Legend
     */
    fun getLegends(ids: List<String>, language: ApiLanguage? = null): List<Legend> {
        return legendsClient.getLegends(ids, language)
    }
}
