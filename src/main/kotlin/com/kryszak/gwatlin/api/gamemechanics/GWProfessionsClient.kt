package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.gamemechanics.model.profession.Profession
import com.kryszak.gwatlin.clients.gamemechanics.ProfessionsClient

/**
 * Client for game mechanic - professions endpoints
 * @see com.kryszak.gwatlin.api.model.achievement.exception.ApiRequestException for errors
 */
class GWProfessionsClient {

    private val professionsClient: ProfessionsClient = ProfessionsClient()

    /**
     * Retrieves all profession ids
     * @return list of all profession ids
     */
    fun getProfessionIds(): List<String> {
        return professionsClient.getProfessionIds()
    }

    /**
     * Retrieves requested pets
     * @param ids list of profession ids
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.gamemechanics.model.profession.Profession
     */
    fun getPets(ids: List<String>, language: String = "en"): List<Profession> {
        return professionsClient.getProfessions(ids, language)
    }

    /**
     * Retrieves all professions
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.gamemechanics.model.profession.Profession
     */
    fun getAllPets(language: String = "en"): List<Profession> {
        return professionsClient.getAllProfessions(language)
    }
}
