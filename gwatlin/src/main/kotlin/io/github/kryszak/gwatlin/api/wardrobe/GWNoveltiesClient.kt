package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.wardrobe.model.Novelty
import io.github.kryszak.gwatlin.clients.wardrobe.NoveltiesClient

/**
 * Client for wardrobe - novelties endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/novelties).
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWNoveltiesClient {

    private val noveltiesClient = NoveltiesClient()

    /**
     * Returns information about novelties that are available in-game
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/novelties)
     */
    @JvmOverloads
    fun getAllNovelties(language: ApiLanguage? = null): List<Novelty> {
        return noveltiesClient.getAllNovelties(language)
    }

    /**
     * Returns all novelty ids
     */
    fun getNoveltyIds(): List<Int> {
        return noveltiesClient.getNoveltyIds()
    }

    /**
     * Returns novelty for given
     * @param id of novelty
     * @param language for response
     */
    fun getNovelty(id: Int, language: ApiLanguage? = null): Novelty {
        return noveltiesClient.getNovelty(id, language)
    }

    /**
     * Returns novelties for given
     * @param ids of novelties
     * @param language for response
     */
    fun getNovelties(ids: List<Int>, language: ApiLanguage? = null): List<Novelty> {
        return noveltiesClient.getNovelties(ids, language)
    }
}