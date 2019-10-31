package com.kryszak.gwatlin.clients.items

import com.kryszak.gwatlin.api.items.model.finisher.Finisher
import com.kryszak.gwatlin.http.BaseHttpClient

internal class FinishersClient : BaseHttpClient() {

    private val finisherEndpoint = "finishers"

    fun getFinisherIds(): List<Int> {
        return getRequest(finisherEndpoint)
    }

    fun getFinishers(ids: List<Int>, language: String): List<Finisher> {
        val params = ids.joinToString(",")
        return getRequest("$finisherEndpoint?ids=$params&lang=$language")
    }
}
