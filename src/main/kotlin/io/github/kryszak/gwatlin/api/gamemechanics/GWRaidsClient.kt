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
    fun getRaids(language: ApiLanguage? = null): List<Raid> {
        return raidClient.getRaids(language)
    }
}