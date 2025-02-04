package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.color.DyeColor
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.clients.gamemechanics.ColorClient

/**
 * Client for color endpoint
 * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/dyes)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWColorsClient {

    private val colorClient = ColorClient()

    /**
     * Returns all dye colors in the game, including localized names and their color component information
     * @param language optional language parameter for request
     */
    @JvmOverloads
    fun getAllDyeColors(language: ApiLanguage? = null): List<DyeColor> {
        return colorClient.getAllColors(language)
    }

    /**
     * Returns list of all dye color ids in the game.
     */
    fun getDyeColorIds(): List<Int> {
        return colorClient.getColorIds()
    }

    /**
     * Returns dye color for given id and language.
     * @param id of color
     * @param language for response
     */
    fun getDyeColor(id: Int, language: ApiLanguage? = null): DyeColor {
        return colorClient.getColor(id, language)
    }

    /**
     * Returns dye colors for given id list and language.
     * @param ids of color
     * @param language for response
     */
    fun getDyeColors(ids: List<Int>, language: ApiLanguage? = null): List<DyeColor> {
        return colorClient.getColors(ids, language)
    }

    /**
     * Returns paged dye colors
     * @param language for response
     */
    fun getPagedColors(pageRequest: PageRequest, language: ApiLanguage? = null): PagedResponse<List<DyeColor>> {
        return colorClient.getPagedColors(pageRequest, language)
    }
}