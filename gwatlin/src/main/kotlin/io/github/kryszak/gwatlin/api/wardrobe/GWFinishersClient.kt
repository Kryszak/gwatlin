package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.api.wardrobe.model.finisher.Finisher
import io.github.kryszak.gwatlin.clients.wardrobe.FinishersClient

/**
 * Client for finishers endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/finishers)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWFinishersClient {

    private val finishersClient: FinishersClient = FinishersClient()

    /**
     * Retrieves list of finisher ids
     */
    fun getFinisherIds(): List<Int> {
        return finishersClient.getFinisherIds()
    }

    /**
     * Retrieves finishers
     * @param ids of finishers
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.wardrobe.model.finisher.Finisher
     */
    @JvmOverloads
    fun getFinishers(ids: List<Int>, language: ApiLanguage? = null): List<Finisher> {
        return finishersClient.getFinishers(ids, language)
    }

    /**
     * Retrieves paged finishers
     * @param language of returned text (default=en)
     */
    @JvmOverloads
    fun getPagedFinishers(pageRequest: PageRequest, language: ApiLanguage? = null): PagedResponse<List<Finisher>> {
        return finishersClient.getPagedFinishers(pageRequest, language)
    }
}
