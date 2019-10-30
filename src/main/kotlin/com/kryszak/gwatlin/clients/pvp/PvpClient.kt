package com.kryszak.gwatlin.clients.pvp

import com.kryszak.gwatlin.api.pvp.model.rank.PvpRank
import com.kryszak.gwatlin.api.pvp.model.season.PvpSeason
import com.kryszak.gwatlin.http.BaseHttpClient

internal class PvpClient : BaseHttpClient() {

    private val pvpEndpoint = "pvp"

    private val ranksEndpoint = "$pvpEndpoint/ranks"

    private val seasonsEndpoint = "$pvpEndpoint/seasons"

    fun getPvpRankIds(): List<Int> {
        return getRequest(ranksEndpoint)
    }

    fun getPvpRanks(ids: List<Int>, language: String): List<PvpRank> {
        val params = ids.joinToString(",")
        return getRequest("$ranksEndpoint?ids=$params&lang=$language")
    }

    fun getPvpSeasonIds(): List<String> {
        return getRequest(seasonsEndpoint)
    }

    fun getPvpSeasons(ids: List<String>, language: String): List<PvpSeason> {
        val params = ids.joinToString(",")
        return getRequest("$seasonsEndpoint?ids=$params&lang=$language")
    }
}
