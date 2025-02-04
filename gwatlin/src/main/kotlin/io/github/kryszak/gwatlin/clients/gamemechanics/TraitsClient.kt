package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.trait.Trait
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class TraitsClient : BaseHttpClient() {

    private val traitsEndpoint: String = "/traits"

    fun getTraitIds(): List<Int> {
        return getRequest(traitsEndpoint)
    }

    fun getTrait(id: Int, language: ApiLanguage?): Trait {
        return getRequest("$traitsEndpoint/$id", language)
    }

    fun getTraits(ids: List<Int>, language: ApiLanguage?): List<Trait> {
        val params = ids.joinToString(",")
        return getRequest("$traitsEndpoint?ids=$params", language)
    }
}
