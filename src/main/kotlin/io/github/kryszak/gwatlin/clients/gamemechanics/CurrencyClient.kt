package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.Currency
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class CurrencyClient : BaseHttpClient() {

    private val currenciesEndpoint = "/currencies"

    fun getCurrencies(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Currency> {
        return getRequest("$currenciesEndpoint?ids=all", language)
    }
}
