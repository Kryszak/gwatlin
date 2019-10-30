package com.kryszak.gwatlin.clients.pvp

import com.kryszak.gwatlin.api.pvp.model.rank.PvpRank
import com.kryszak.gwatlin.http.BaseHttpClient

internal class PvpClient : BaseHttpClient() {

    private val pvpEndpoint = "pvp"

    private val ranksEndpoint = "$pvpEndpoint/ranks"

    fun getPvpRankIds(): List<Int> {
        return getRequest(ranksEndpoint)
    }

    fun getPvpRanks(ids: List<Int>, language: String): List<PvpRank> {
        val params = ids.joinToString(",")
        return getRequest("$ranksEndpoint?ids=$params&lang=$language")
    }
}
