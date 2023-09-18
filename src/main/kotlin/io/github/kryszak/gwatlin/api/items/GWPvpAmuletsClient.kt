package io.github.kryszak.gwatlin.api.items

import io.github.kryszak.gwatlin.api.items.model.pvp.amulet.PvpAmulet
import io.github.kryszak.gwatlin.clients.items.PvpAmuletsClient

/**
 * Client for pvp amulets endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/pvp/amulets)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
     * @see io.github.kryszak.gwatlin.api.items.model.pvp.amulet.PvpAmulet
     */
    @JvmOverloads
    fun getPvpAmulets(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<PvpAmulet> {
        return pvpAmuletsClient.getPvpAmulets(ids, language)
    }
}