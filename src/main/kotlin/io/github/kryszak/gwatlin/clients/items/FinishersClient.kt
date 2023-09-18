package io.github.kryszak.gwatlin.clients.items

import io.github.kryszak.gwatlin.api.items.model.finisher.Finisher
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class FinishersClient : BaseHttpClient() {

    private val finisherEndpoint = "finishers"

    fun getFinisherIds(): List<Int> {
        return getRequest(finisherEndpoint)
    }

    fun getFinishers(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Finisher> {
        val params = ids.joinToString(",")
        return getRequest("$finisherEndpoint?ids=$params", language)
    }
}
