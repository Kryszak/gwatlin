package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
import com.kryszak.gwatlin.clients.gamemechanics.MasteriesClient

/**
 * Client for game mechanic - masteries endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWMasteriesClient {

    private val masteriesClient: MasteriesClient = MasteriesClient()

    /**
     * Retrieves list of all mastery ids
     * @return mastery ids list
     */
    fun getMasteriesIds(): List<Int> {
        return masteriesClient.getMasteriesIds()
    }

    /**
     * Retrieves specific mastery
     * @param id of mastery
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
     */
    fun getMastery(id: Int, language: ApiLanguage? = null): Mastery {
        return masteriesClient.getMastery(id, language)
    }

    /**
     * Retrieves list of masteries
     * @param ids list of mastery ids
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
     */
    fun getMasteries(ids: List<Int>, language: ApiLanguage? = null): List<Mastery> {
        return masteriesClient.getMasteries(ids, language)
    }

    /**
     * Retrieves all masteries
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
     */
    fun getAllMasteries(language: ApiLanguage? = null): List<Mastery> {
        return masteriesClient.getAllMasteries(language)
    }
}
