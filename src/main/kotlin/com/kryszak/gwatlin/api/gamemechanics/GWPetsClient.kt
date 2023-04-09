package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.gamemechanics.model.pet.Pet
import com.kryszak.gwatlin.clients.gamemechanics.PetsClient

/**
 * Client for game mechanic - pets endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWPetsClient {

    private val petsClient: PetsClient = PetsClient()

    /**
     * Retrieves all pet ids
     * @return list of all pet ids
     */
    fun getPetIds(): List<Int> {
        return petsClient.getPetIds()
    }

    /**
     * Retrieves requested pets
     * @param ids list of pet ids
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.gamemechanics.model.pet.Pet
     */
    @JvmOverloads
    fun getPets(ids: List<Int>, language: ApiLanguage? = null): List<Pet> {
        return petsClient.getPets(ids, language)
    }

    /**
     * Retrieves all pets
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.gamemechanics.model.pet.Pet
     */
    @JvmOverloads
    fun getAllPets(language: ApiLanguage? = null): List<Pet> {
        return petsClient.getAllPets(language)
    }
}
