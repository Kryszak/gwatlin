package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.api.wardrobe.model.Glider
import io.github.kryszak.gwatlin.clients.wardrobe.GlidersClient

/**
 * Client for wardrobe - novelties endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/gliders).
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWGlidersClient {

    private val gliderClient = GlidersClient()

    /**
     * Returns all ids of available gliders
     */
    fun getGliderIds(): List<Int> {
        return gliderClient.getGliderIds()
    }

    /**
     * Returns glider for given
     * @param id of glider
     * @param language for response
     */
    fun getGlider(id: Int, language: ApiLanguage? = null): Glider {
        return gliderClient.getGlider(id, language)
    }

    /**
     * Returns gliders for given
     * @param ids of gliders
     * @param language for response
     */
    fun getGliders(ids: List<Int>, language: ApiLanguage? = null): List<Glider> {
        return gliderClient.getGliders(ids, language)
    }

    /**
     * Returns paged gliders
     * @param language for response
     */
    fun getPagedGliders(pageRequest: PageRequest, language: ApiLanguage? = null): PagedResponse<List<Glider>> {
        return gliderClient.getPagedGliders(pageRequest, language)
    }
}