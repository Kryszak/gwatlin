package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.trait.Trait
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class TraitsClient : BaseHttpClient() {

    private val traitsEndpoint: String = "traits"

    fun getTraitIds(): List<Int> {
        return getRequest(traitsEndpoint)
    }

    fun getTraits(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Trait> {
        val params = ids.joinToString(",")
        return getRequest("$traitsEndpoint?ids=$params", language)
    }
}
