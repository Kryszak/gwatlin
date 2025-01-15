package io.github.kryszak.gwatlin.clients.wardrobe

import io.github.kryszak.gwatlin.api.wardrobe.model.skins.Skin
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class SkinsClient : BaseHttpClient() {

    private val skinsEndpoint = "/skins"

    fun getSkinIds(): List<Int> {
        return getRequest(skinsEndpoint)
    }

    fun getSkins(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Skin> {
        val params = ids.joinToString(",")
        return getRequest("$skinsEndpoint?ids=$params", language)
    }
}