package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.pet.Pet
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class PetsClient : BaseHttpClient() {

    private val petsEndpoint = "/pets"

    fun getPetIds(): List<Int> {
        return getRequest(petsEndpoint)
    }

    fun getPets(ids: List<Int>, language: ApiLanguage?): List<Pet> {
        val params = ids.joinToString(",")
        return getRequest("$petsEndpoint?ids=$params", listOf(), language)
    }

    fun getAllPets(language: ApiLanguage?): List<Pet> {
        return getRequest("$petsEndpoint?ids=all", listOf(), language)
    }

    fun getPagedPets(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<Pet>> {
        return getPagedRequest("$petsEndpoint?${pageRequest.toQueryParams()}", listOf(), language)
    }
}
