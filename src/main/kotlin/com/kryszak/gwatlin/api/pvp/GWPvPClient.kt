package com.kryszak.gwatlin.api.pvp

import com.kryszak.gwatlin.api.pvp.model.rank.PvpRank
import com.kryszak.gwatlin.api.pvp.model.season.PvpSeason
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
    fun getPvpRanks(ids: List<Int>, language: String = "en"): List<PvpRank> {
        return pvpClient.getPvpRanks(ids, language)
    }

    /**
     * Retrieves list of all season ids
     */
    fun getPvpSeasonIds(): List<String> {
        return pvpClient.getPvpSeasonIds()
    }

    /**
     * Retrieves specific pvp seasons
     * @param ids of seasons
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.pvp.model.season.PvpSeason
     */
    fun getPvpSeasons(ids: List<String>, language: String = "en"): List<PvpSeason> {
        return pvpClient.getPvpSeasons(ids, language)
    }
}
