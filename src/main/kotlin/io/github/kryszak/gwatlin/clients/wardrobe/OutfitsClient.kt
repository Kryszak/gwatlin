package io.github.kryszak.gwatlin.clients.wardrobe

import io.github.kryszak.gwatlin.api.wardrobe.model.outfit.Outfit
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class OutfitsClient : BaseHttpClient() {

    private val outfitsEndpoint = "/outfits"

    fun getOutfitsIds(): List<Int> {
        return getRequest(outfitsEndpoint)
    }

    fun getOutfits(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Outfit> {
        val params = ids.joinToString(",")
        return getRequest("$outfitsEndpoint?ids=$params", language)
    }

    fun getAllOutfits(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Outfit> {
        return getRequest("$outfitsEndpoint?ids=all", language)
    }
}
