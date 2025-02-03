package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.wardrobe.model.Mini
import io.github.kryszak.gwatlin.clients.wardrobe.MinisClient

/**
 * Client for minis endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/minis)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWMinisClient {

    private val minisClient = MinisClient()

    /**
     * Returns all minis in the game
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/minis)
     * @param language for response
     */
    fun getAllMinis(language: ApiLanguage? = null): List<Mini> {
        return minisClient.getAllMinis(language)
    }

    /**
     * Returns all mini ids
     */
    fun getMiniIds(): List<Int> {
        return minisClient.getMiniIds()
    }

    /**
     * Returns mini for given
     * @param id of mini
     * @param language for response
     */
    fun getMini(id: Int, language: ApiLanguage? = null): Mini {
        return minisClient.getMini(id, language)
    }

    /**
     * Returns mini for given
     * @param id of mini
     * @param language for response
     */
    fun getMinis(ids: List<Int>, language: ApiLanguage? = null): List<Mini> {
        return minisClient.getMinis(ids, language)
    }
}