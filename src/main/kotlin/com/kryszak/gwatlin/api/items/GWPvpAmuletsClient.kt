package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.items.model.pvp.amulet.PvpAmulet
import com.kryszak.gwatlin.clients.items.PvpAmuletsClient

/**
 * Client for pvp amulets endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWPvpAmuletsClient {

    private val pvpAmuletsClient: PvpAmuletsClient = PvpAmuletsClient()

    /**
     * Retrieves list of all pvp amulet ids
     */
    fun getPvpAmuletIds(): List<Int> {
        return pvpAmuletsClient.getPvpAmuletIds()
    }

    /**
     * Retrieves specific pvp amulets
     * @param ids of amulets
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.items.model.pvp.amulet.PvpAmulet
     */
    fun getPvpAmulets(ids: List<Int>, language: ApiLanguage? = null): List<PvpAmulet> {
        return pvpAmuletsClient.getPvpAmulets(ids, language)
    }
}