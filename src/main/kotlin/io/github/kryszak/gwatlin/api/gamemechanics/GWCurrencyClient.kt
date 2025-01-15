package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.Currency
import io.github.kryszak.gwatlin.clients.gamemechanics.CurrencyClient

/**
 * Client for currency endpoint
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWCurrencyClient {

    private val currencyClient = CurrencyClient()

    /**
     * Returns a list of the currencies contained in the account wallet
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/currencies)
     */
    @JvmOverloads
    fun getCurrencies(language: ApiLanguage? = null): List<Currency> {
        return currencyClient.getCurrencies(language)
    }
}