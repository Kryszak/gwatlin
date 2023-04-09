package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.gamemechanics.model.specialization.Specialization
import com.kryszak.gwatlin.clients.gamemechanics.SpecializationClient

/**
 * Client for game mechanic - specialization endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
     * @see com.kryszak.gwatlin.api.gamemechanics.model.specialization.Specialization
     */
    fun getSpecialization(id: Int, language: ApiLanguage? = null): Specialization {
        return specializationClient.getSpecialization(id, language)
    }
}