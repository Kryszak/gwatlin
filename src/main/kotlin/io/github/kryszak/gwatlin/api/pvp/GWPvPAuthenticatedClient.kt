package io.github.kryszak.gwatlin.api.pvp

import io.github.kryszak.gwatlin.api.pvp.model.game.PvpGame
import io.github.kryszak.gwatlin.api.pvp.model.stats.PvpStanding
import io.github.kryszak.gwatlin.api.pvp.model.stats.PvpStats
import io.github.kryszak.gwatlin.clients.pvp.PvpAuthenticatedClient

/**
 * Client for pvp authenticated endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/pvp)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWPvPAuthenticatedClient(apiKey: String) {

    private val pvpAuthenticatedClient: PvpAuthenticatedClient = PvpAuthenticatedClient(apiKey)

    /**
     * Retrieves information about wins and losses in the account's PvP matches
     */
    fun getPvpStats(): PvpStats {
        return pvpAuthenticatedClient.getPvpStats()
    }

    /**
     * Retrieves list of all account's pvp games
     */
    fun getPvpGameIds(): List<String> {
        return pvpAuthenticatedClient.getPvpGameIds()
    }

    /**
     * Retrieves specific account games
     * @param ids of games
     */
    fun getPvpGames(ids: List<String>): List<PvpGame> {
        return pvpAuthenticatedClient.getPvpGames(ids)
    }

    /**
     * Retrieves information about player pvp standings
     */
    fun getPvpStanding(): PvpStanding {
        return pvpAuthenticatedClient.getPvpStandings()
    }
}
