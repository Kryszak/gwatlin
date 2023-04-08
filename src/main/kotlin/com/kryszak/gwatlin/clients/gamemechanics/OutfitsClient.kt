package com.kryszak.gwatlin.clients.gamemechanics

import com.kryszak.gwatlin.api.gamemechanics.model.outfit.Outfit
import com.kryszak.gwatlin.http.BaseHttpClient

internal class OutfitsClient : BaseHttpClient() {

    private val outfitsEndpoint = "outfits"

    fun getOutfitsIds(): List<Int> {
        return getRequest(outfitsEndpoint)
    }

    fun getOutfits(ids: List<Int>, language: String): List<Outfit> {
        val params = ids.joinToString(",")
        return getRequest("$outfitsEndpoint?ids=$params", language)
    }

    fun getAllOutfits(language: String): List<Outfit> {
        return getRequest("$outfitsEndpoint?ids=all", language)
    }
}
