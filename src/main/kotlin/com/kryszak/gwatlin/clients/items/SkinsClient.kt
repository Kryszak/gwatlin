package com.kryszak.gwatlin.clients.items

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.items.model.skins.Skin
import com.kryszak.gwatlin.http.BaseHttpClient

internal class SkinsClient : BaseHttpClient() {

    private val skinsEndpoint = "skins"

    fun getSkinIds(): List<Int> {
        return getRequest(skinsEndpoint)
    }

    fun getSkins(ids: List<Int>, language: ApiLanguage?): List<Skin> {
        val params = ids.joinToString(",")
        return getRequest("$skinsEndpoint?ids=$params", language)
    }
}