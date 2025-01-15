package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.Title
import io.github.kryszak.gwatlin.clients.gamemechanics.TitleClient

/**
 * Client for title endpoint
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWTitlesClient {

    private val titleClient = TitleClient()

    /**
     * Returns information about the titles that are in the game
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/titles)
     */
    @JvmOverloads
    fun getTitles(language: ApiLanguage? = null): List<Title> {
        return titleClient.getTitles(language)
    }
}