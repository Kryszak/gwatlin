package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.Title
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.clients.gamemechanics.TitleClient

/**
 * Client for title endpoint
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWTitlesClient {

    private val titleClient = TitleClient()

    /**
     * Returns information about all titles that are in the game
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/titles)
     * @param language of response
     */
    @JvmOverloads
    fun getAllTitles(language: ApiLanguage? = null): List<Title> {
        return titleClient.getAllTitles(language)
    }

    /**
     * Returns list of all title ids
     */
    fun getTitleIds(): List<Int> {
        return titleClient.getTitleIds()
    }

    /**
     * Returns title for given id
     * @param id of title
     * @param language of response
     */
    fun getTitle(id: Int, language: ApiLanguage? = null): Title {
        return titleClient.getTitle(id, language)
    }

    /**
     * Returns titles for given id list
     * @param ids of titles
     * @param language of response
     */
    fun getTitles(ids: List<Int>, language: ApiLanguage? = null): List<Title> {
        return titleClient.getTitles(ids, language)
    }

    /**
     * Returns paged titles
     * @param language of response
     */
    fun getPagedTitles(pageRequest: PageRequest, language: ApiLanguage? = null): PagedResponse<List<Title>> {
        return titleClient.getPagedTitles(pageRequest, language)
    }
}