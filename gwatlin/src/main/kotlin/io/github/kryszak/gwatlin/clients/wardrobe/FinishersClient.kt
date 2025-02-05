package io.github.kryszak.gwatlin.clients.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.api.wardrobe.model.finisher.Finisher
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class FinishersClient : BaseHttpClient() {

    private val finisherEndpoint = "/finishers"

    fun getFinisherIds(): List<Int> {
        return getRequest(finisherEndpoint)
    }

    fun getFinishers(ids: List<Int>, language: ApiLanguage?): List<Finisher> {
        val params = ids.joinToString(",")
        return getRequest("$finisherEndpoint?ids=$params", language)
    }

    fun getPagedFinishers(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<Finisher>> {
        return getPagedRequest("$finisherEndpoint?page=${pageRequest.page}&page_size=${pageRequest.size}", language)
    }
}
