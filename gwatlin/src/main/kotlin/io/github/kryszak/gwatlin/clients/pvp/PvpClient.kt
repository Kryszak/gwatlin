package io.github.kryszak.gwatlin.clients.pvp

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.pvp.model.rank.PvpRank
import io.github.kryszak.gwatlin.api.pvp.model.season.PvpSeason
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class PvpClient : BaseHttpClient() {

    private val pvpEndpoint = "/pvp"

    private val ranksEndpoint = "$pvpEndpoint/ranks"

    private val seasonsEndpoint = "$pvpEndpoint/seasons"

    fun getPvpRankIds(): List<Int> {
        return getRequest(ranksEndpoint)
    }

    fun getPvpRanks(ids: List<Int>, language: ApiLanguage?): List<PvpRank> {
        val params = ids.joinToString(",")
        return getRequest("$ranksEndpoint?ids=$params", listOf(), language)
    }

    fun getPvpSeasonIds(): List<String> {
        return getRequest(seasonsEndpoint)
    }

    fun getPvpSeasons(ids: List<String>, language: ApiLanguage?): List<PvpSeason> {
        val params = ids.joinToString(",")
        return getRequest("$seasonsEndpoint?ids=$params", listOf(), language)
    }
}
