package io.github.kryszak.gwatlin.api.pvp

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.pvp.model.rank.PvpRank
import io.github.kryszak.gwatlin.api.pvp.model.season.PvpSeason
import io.github.kryszak.gwatlin.clients.pvp.PvpClient

/**
 * Client for pvp unauthenticated endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/pvp)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
     * @see io.github.kryszak.gwatlin.api.pvp.model.rank.PvpRank
     */
    @JvmOverloads
    fun getPvpRanks(ids: List<Int>, language: ApiLanguage? = null): List<PvpRank> {
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
     * @see io.github.kryszak.gwatlin.api.pvp.model.season.PvpSeason
     */
    @JvmOverloads
    fun getPvpSeasons(ids: List<String>, language: ApiLanguage? = null): List<PvpSeason> {
        return pvpClient.getPvpSeasons(ids, language)
    }
}
