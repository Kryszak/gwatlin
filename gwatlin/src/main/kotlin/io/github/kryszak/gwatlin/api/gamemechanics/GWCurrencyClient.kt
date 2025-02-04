package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.Currency
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.clients.gamemechanics.CurrencyClient

/**
 * Client for currency endpoint
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWCurrencyClient {

    private val currencyClient = CurrencyClient()

    /**
     * Returns a list of all currencies contained in the account wallet
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/currencies)
     */
    @JvmOverloads
    fun getAllCurrencies(language: ApiLanguage? = null): List<Currency> {
        return currencyClient.getAllCurrencies(language)
    }

    /**
     * Returns a list of all currency ids
     */
    fun getCurrencyIds(): List<Int> {
        return currencyClient.getCurrencyIds()
    }

    /**
     * Returns currency for given ids
     * @param id of currency
     * @return requested currency
     */
    fun getCurrency(id: Int, language: ApiLanguage? = null): Currency {
        return currencyClient.getCurrency(id, language)
    }

    /**
     * Returns a list of currencies for given ids
     * @param ids of currencies
     * @return list with requested currencies
     */
    fun getCurrencies(ids: List<Int>, language: ApiLanguage? = null): List<Currency> {
        return currencyClient.getCurrencies(ids, language)
    }

    fun getPagedCurrencies(pageRequest: PageRequest, language: ApiLanguage? = null): PagedResponse<List<Currency>> {
        return currencyClient.getPagedCurrencies(pageRequest, language)
    }
}