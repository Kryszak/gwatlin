package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.dungeon.Dungeon
import io.github.kryszak.gwatlin.clients.gamemechanics.DungeonsClient

/**
 * Client for dungeons endpoint
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWDungeonsClient {

    private val dungeonsClient = DungeonsClient()

    /**
     * Returns details about each dungeon and it's associated paths
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/dungeons)
     */
    @JvmOverloads
    fun getDungeons(language: ApiLanguage? = null): List<Dungeon> {
        return dungeonsClient.getDungeons(language)
    }
}