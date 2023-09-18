package io.github.kryszak.gwatlin.api.items

import io.github.kryszak.gwatlin.api.items.model.finisher.Finisher
import io.github.kryszak.gwatlin.clients.items.FinishersClient

/**
 * Client for finishers endpoints
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
     * @see io.github.kryszak.gwatlin.api.items.model.finisher.Finisher
     */
    @JvmOverloads
    fun getFinishers(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<Finisher> {
        return finishersClient.getFinishers(ids, language)
    }
}
