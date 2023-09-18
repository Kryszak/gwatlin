package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.pet.Pet
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class PetsClient : BaseHttpClient() {

    private val petsEndpoint = "pets"

    fun getPetIds(): List<Int> {
        return getRequest(petsEndpoint)
    }

    fun getPets(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Pet> {
        val params = ids.joinToString(",")
        return getRequest("$petsEndpoint?ids=$params", language)
    }

    fun getAllPets(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Pet> {
        return getRequest("$petsEndpoint?ids=all", language)
    }
}
