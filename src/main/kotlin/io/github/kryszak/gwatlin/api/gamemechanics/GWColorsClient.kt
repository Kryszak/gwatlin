package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.color.DyeColor
import io.github.kryszak.gwatlin.clients.gamemechanics.ColorClient

/**
 * Client for color endpoint
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWColorsClient {

    private val colorClient = ColorClient()

    /**
     * Returns all dye colors in the game, including localized names and their color component information
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/dyes)
     */
    @JvmOverloads
    fun getAllDyeColors(language: ApiLanguage? = null): List<DyeColor> {
        return colorClient.getAllColors(language)
    }
}