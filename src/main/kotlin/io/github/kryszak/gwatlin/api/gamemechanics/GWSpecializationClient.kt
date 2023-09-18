package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.specialization.Specialization
import io.github.kryszak.gwatlin.clients.gamemechanics.SpecializationClient

/**
 * Client for game mechanic - specialization endpoints
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWSpecializationClient {

    private val specializationClient: SpecializationClient = SpecializationClient()

    /**
     * Retrieves list of all specialization ids
     */
    fun getSpecializationIds(): List<Int> {
        return specializationClient.getSpecializationIds()
    }

    /**
     * Retrieves specific specialization
     * @param id of specialization
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.specialization.Specialization
     */
    @JvmOverloads
    fun getSpecialization(id: Int, language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): Specialization {
        return specializationClient.getSpecialization(id, language)
    }
}