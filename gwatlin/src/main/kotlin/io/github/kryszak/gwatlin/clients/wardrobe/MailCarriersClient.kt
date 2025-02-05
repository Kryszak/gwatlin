package io.github.kryszak.gwatlin.clients.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.api.wardrobe.model.MailCarrier
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class MailCarriersClient : BaseHttpClient() {

    private val endpoint = "/mailcarriers"

    fun getMailCarrierIds(): List<Int> {
        return getRequest(endpoint)
    }

    fun getMailCarrier(id: Int, language: ApiLanguage?): MailCarrier {
        return getRequest("$endpoint/$id", listOf(), language)
    }

    fun getMailCarriers(ids: List<Int>, language: ApiLanguage?): List<MailCarrier> {
        val params = ids.joinToString(",")
        return getRequest("$endpoint?ids=$params", listOf(), language)
    }

    fun getPagedMailCarriers(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<MailCarrier>> {
        return getPagedRequest("$endpoint?${pageRequest.toQueryParams()}", listOf(), language)
    }
}