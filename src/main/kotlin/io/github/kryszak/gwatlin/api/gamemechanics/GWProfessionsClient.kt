package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.profession.Profession
import io.github.kryszak.gwatlin.clients.gamemechanics.ProfessionsClient

/**
 * Client for game mechanic - professions endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/professions).
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.profession.Profession
     */
    @JvmOverloads
    fun getProfessions(
        ids: List<String>,
        language: io.github.kryszak.gwatlin.api.ApiLanguage? = null
    ): List<Profession> {
        return professionsClient.getProfessions(ids, language)
    }

    /**
     * Retrieves all professions
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.profession.Profession
     */
    @JvmOverloads
    fun getAllProfessions(language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<Profession> {
        return professionsClient.getAllProfessions(language)
    }
}
