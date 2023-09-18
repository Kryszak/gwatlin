package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.pet.Pet
import io.github.kryszak.gwatlin.clients.gamemechanics.PetsClient

/**
 * Client for game mechanic - pets endpoints
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.pet.Pet
     */
    @JvmOverloads
    fun getPets(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<Pet> {
        return petsClient.getPets(ids, language)
    }

    /**
     * Retrieves all pets
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.pet.Pet
     */
    @JvmOverloads
    fun getAllPets(language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<Pet> {
        return petsClient.getAllPets(language)
    }
}
