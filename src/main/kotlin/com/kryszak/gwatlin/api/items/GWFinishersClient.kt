package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.items.model.finisher.Finisher
import com.kryszak.gwatlin.clients.items.FinishersClient

/**
 * Client for finishers endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
     * @see com.kryszak.gwatlin.api.items.model.finisher.Finisher
     */
    fun getFinishers(ids: List<Int>, language: String = "en"): List<Finisher> {
        return finishersClient.getFinishers(ids, language)
    }
}
