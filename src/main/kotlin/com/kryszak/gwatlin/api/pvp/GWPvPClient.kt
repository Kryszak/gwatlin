package com.kryszak.gwatlin.api.pvp

import com.kryszak.gwatlin.api.pvp.model.rank.PvpRank
import com.kryszak.gwatlin.clients.pvp.PvpClient

/**
 * Client for pvp unauthenticated endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWPvPClient {

    private val pvpClient: PvpClient = PvpClient()

    /**
     * Retrieves pvp rank ids
     */
    fun getPvpRankIds(): List<Int> {
        return pvpClient.getPvpRankIds()
    }

    /**
     * Retrieves specific pvp ranks
     * @param ids of ranks
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.pvp.model.rank.PvpRank
     */
    fun getPvpRanks(ids:List<Int>, language: String = "en"): List<PvpRank> {
        return pvpClient.getPvpRanks(ids, language)
    }
}
