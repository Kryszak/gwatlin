package io.github.kryszak.gwatlin.clients.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.wardrobe.model.MailCarrier
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class MailCarriersClient : BaseHttpClient() {

    private val endpoint = "/mailcarriers"

    fun getMailCarrierIds(): List<Int> {
        return getRequest(endpoint)
    }

    fun getMailCarrier(id: Int, language: ApiLanguage?): MailCarrier {
        return getRequest("$endpoint/$id", language)
    }

    fun getMailCarriers(ids: List<Int>, language: ApiLanguage?): List<MailCarrier> {
        val params = ids.joinToString(",")
        return getRequest("$endpoint?ids=$params", language)
    }
}