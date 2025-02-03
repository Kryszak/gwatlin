package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.wardrobe.model.Skiff
import io.github.kryszak.gwatlin.clients.wardrobe.SkiffsClient

/**
 * Client for minis endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/skiffs)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWSkiffsClient {

    private val skiffsClient = SkiffsClient()

    /**
     * Returns ids of all available skiffs
     */
    fun getSkiffIds(): List<Int> {
        return skiffsClient.getSkiffIds()
    }

    /**
     * Returns skiff for given
     * @param id of skiff
     * @param language for response
     */
    fun getSkiff(id: Int, language: ApiLanguage? = null): Skiff {
        return skiffsClient.getSkiff(id, language)
    }

    /**
     * Returns skiffs for given
     * @param ids of skiffs
     * @param language for response
     */
    fun getSkiffs(ids: List<Int>, language: ApiLanguage? = null): List<Skiff> {
        return skiffsClient.getSkiffs(ids, language)
    }
}