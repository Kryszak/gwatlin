package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.raid.Raid
import io.github.kryszak.gwatlin.clients.gamemechanics.RaidClient

/**
 * Client for raid endpoint
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWRaidsClient {

    private val raidClient: RaidClient = RaidClient()

    /**
     * Resource returns details about each raid and it's associated wings
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/raids)
     */
    @JvmOverloads
    fun getAllRaids(language: ApiLanguage? = null): List<Raid> {
        return raidClient.getAllRaids(language)
    }

    /**
     * Returns list of all raid ids
     */
    fun getRaidIds(): List<String> {
        return raidClient.getRaidIds()
    }

    /**
     * Returns requested raid
     * @param id of raid
     * @param language for response
     */
    fun getRaid(id: String, language: ApiLanguage? = null): Raid {
        return raidClient.getRaid(id, language)
    }

    /**
     * Returns requested raids
     * @param ids of raids
     * @param language for response
     */
    fun getRaids(ids: List<String>, language: ApiLanguage? = null): List<Raid> {
        return raidClient.getRaids(ids, language)
    }
}