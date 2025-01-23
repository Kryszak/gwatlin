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
     * @param language language for response
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/dungeons)
     */
    @JvmOverloads
    fun getAllDungeons(language: ApiLanguage? = null): List<Dungeon> {
        return dungeonsClient.getDungeons(language)
    }

    /**
     * Returns list of all dungeon ids
     */
    fun getDungeonIds(): List<String> {
        return dungeonsClient.getDungeonIds()
    }

    /**
     * Returns dungeon for given id
     * @param id of dungeon
     * @param language language for response
     */
    fun getDungeon(id: String, language: ApiLanguage? = null): Dungeon {
        return dungeonsClient.getDungeon(id, language)
    }

    /**
     * Returns dungeons for given id list
     * @param ids of dungeons
     * @param language language for response
     */
    fun getDungeons(ids: List<String>, language: ApiLanguage? = null): List<Dungeon> {
        return dungeonsClient.getDungeons(ids, language)
    }
}