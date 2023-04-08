package com.kryszak.gwatlin.clients.gamemechanics

import com.kryszak.gwatlin.api.gamemechanics.model.pet.Pet
import com.kryszak.gwatlin.http.BaseHttpClient

internal class PetsClient : BaseHttpClient() {

    private val petsEndpoint = "pets"

    fun getPetIds(): List<Int> {
        return getRequest(petsEndpoint)
    }

    fun getPets(ids: List<Int>, language: String): List<Pet> {
        val params = ids.joinToString(",")
        return getRequest("$petsEndpoint?ids=$params", language)
    }

    fun getAllPets(language: String): List<Pet> {
        return getRequest("$petsEndpoint?ids=all", language)
    }
}
