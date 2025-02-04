package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.Currency
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class CurrencyClient : BaseHttpClient() {

    private val currenciesEndpoint = "/currencies"

    fun getAllCurrencies(language: ApiLanguage?): List<Currency> {
        return getRequest("$currenciesEndpoint?ids=all", language)
    }

    fun getCurrencyIds(): List<Int> {
        return getRequest(currenciesEndpoint)
    }

    fun getCurrency(id: Int, language: ApiLanguage?): Currency {
        return getRequest("$currenciesEndpoint/$id", language)
    }

    fun getCurrencies(ids: List<Int>, language: ApiLanguage?): List<Currency> {
        val params = ids.joinToString(",")
        return getRequest("$currenciesEndpoint?ids=$params", language)
    }

    fun getPagedCurrencies(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<Currency>> {
        return getPagedRequest("$currenciesEndpoint?page=${pageRequest.page}&page_size=${pageRequest.size}", language)
    }
}
