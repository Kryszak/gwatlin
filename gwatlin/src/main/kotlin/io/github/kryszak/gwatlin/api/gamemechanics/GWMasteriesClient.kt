package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
import io.github.kryszak.gwatlin.clients.gamemechanics.MasteriesClient

/**
 * Client for game mechanic - masteries endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/masteries).
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
     */
    @JvmOverloads
    fun getMastery(id: Int, language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): Mastery {
        return masteriesClient.getMastery(id, language)
    }

    /**
     * Retrieves list of masteries
     * @param ids list of mastery ids
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
     */
    @JvmOverloads
    fun getMasteries(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<Mastery> {
        return masteriesClient.getMasteries(ids, language)
    }

    /**
     * Retrieves all masteries
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
     */
    @JvmOverloads
    fun getAllMasteries(language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<Mastery> {
        return masteriesClient.getAllMasteries(language)
    }
}
