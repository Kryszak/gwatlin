package io.github.kryszak.gwatlin.clients.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.wardrobe.model.outfit.Outfit
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class OutfitsClient : BaseHttpClient() {

    private val outfitsEndpoint = "/outfits"

    fun getOutfitsIds(): List<Int> {
        return getRequest(outfitsEndpoint)
    }

    fun getOutfits(ids: List<Int>, language: ApiLanguage?): List<Outfit> {
        val params = ids.joinToString(",")
        return getRequest(outfitsEndpoint, listOf("ids" to params), language)
    }

    fun getAllOutfits(language: ApiLanguage?): List<Outfit> {
        return getRequest(outfitsEndpoint, listOf("ids" to "all"), language)
    }
}
