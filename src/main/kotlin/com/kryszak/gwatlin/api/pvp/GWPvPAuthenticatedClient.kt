package com.kryszak.gwatlin.api.pvp

import com.kryszak.gwatlin.api.pvp.model.game.PvpGame
import com.kryszak.gwatlin.api.pvp.model.stats.PvpStanding
import com.kryszak.gwatlin.api.pvp.model.stats.PvpStats
import com.kryszak.gwatlin.clients.pvp.PvpAuthenticatedClient

/**
 * Client for pvp authenticated endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
