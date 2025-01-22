package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.specialization.Specialization
import io.github.kryszak.gwatlin.clients.gamemechanics.SpecializationClient

/**
 * Client for game mechanic - specialization endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/specializations).
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
    fun getSpecialization(id: Int, language: ApiLanguage? = null): Specialization {
        return specializationClient.getSpecialization(id, language)
    }

    /**
     * Retrieves list of specializations
     * @param ids of specializations
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.specialization.Specialization
     */
    @JvmOverloads
    fun getSpecializations(ids: List<Int>, language: ApiLanguage? = null): List<Specialization> {
        return specializationClient.getSpecializations(ids, language)
    }
}