package com.kryszak.gwatlin.clients.gamemechanics

import com.kryszak.gwatlin.api.gamemechanics.model.trait.Trait
import com.kryszak.gwatlin.http.BaseHttpClient

internal class TraitsClient : BaseHttpClient() {

    private val traitsEndpoint: String = "traits"

    fun getTraitIds(): List<Int> {
        return getRequest(traitsEndpoint)
    }

    fun getTraits(ids: List<Int>, language: String): List<Trait> {
        val params = ids.joinToString(",")
        return getRequest("$traitsEndpoint?ids=$params", language)
    }
}
